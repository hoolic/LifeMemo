package com.dsw.datepicker;

import java.util.Calendar;
import java.util.List;

import com.dsw.calendarview.R;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;

public class MonthDateView extends View {
	private static final int NUM_COLUMNS = 7;
	private static final int NUM_ROWS = 6;
	private Paint mPaint;
	private int mDayColor = Color.parseColor("#000000");
	private int mSelectDayColor = Color.parseColor("#ffffff");
	private int mSelectBGColor = Color.parseColor("#1FC2F3");
	private int mCurrentColor = Color.parseColor("#ff0000");
	private int mCurrYear,mCurrMonth,mCurrDay;
	private int mSelYear,mSelMonth,mSelDay;
	private int mColumnSize,mRowSize;
	private DisplayMetrics mDisplayMetrics;
	private int mDaySize = 18;
	private TextView tv_date,tv_week;
	private int weekRow;
	private int [][] daysString;
	private int mCircleRadius = 6;
	private DateClick dateClick;
	private int mCircleColor = Color.parseColor("#ff0000");
	private List<Integer> daysHasThingList;
	public MonthDateView(Context context, AttributeSet attrs) {
		super(context, attrs);
		mDisplayMetrics = getResources().getDisplayMetrics();
		Calendar calendar = Calendar.getInstance();
		mPaint = new Paint();
		mCurrYear = calendar.get(Calendar.YEAR);
		mCurrMonth = calendar.get(Calendar.MONTH);
		mCurrDay = calendar.get(Calendar.DATE);
		setSelectYearMonth(mCurrYear,mCurrMonth,mCurrDay);
	}
	
	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		super.onMeasure(widthMeasureSpec, heightMeasureSpec);
	}
	
	@Override
	protected void onDraw(Canvas canvas) {
		initSize();
		daysString = new int[6][7];
		mPaint.setTextSize(mDaySize*mDisplayMetrics.scaledDensity);
		String dayString;
		int mMonthDays = DateUtils.getMonthDays(mSelYear, mSelMonth);
		int weekNumber = DateUtils.getFirstDayWeek(mSelYear, mSelMonth);
		Log.d("DateView", "DateView:" + mSelMonth+" month week " + weekNumber );
		for(int day = 0;day < mMonthDays;day++){
			dayString = (day + 1) + "";
			int column = (day+weekNumber - 1) % 7;
			int row = (day+weekNumber - 1) / 7;
			daysString[row][column]=day + 1;
			int startX = (int) (mColumnSize * column + (mColumnSize - mPaint.measureText(dayString))/2);
			int startY = (int) (mRowSize * row + mRowSize/2 - (mPaint.ascent() + mPaint.descent())/2);
			if(dayString.equals(mSelDay+"")){
				//draw the background triangle
				int startRecX = mColumnSize * column;
				int startRecY = mRowSize * row;
				int endRecX = startRecX + mColumnSize;
				int endRecY = startRecY + mRowSize;
				mPaint.setColor(mSelectBGColor);
				canvas.drawRect(startRecX, startRecY, endRecX, endRecY, mPaint);
				//keep track of rows, which week
				weekRow = row + 1;
			}
			//draw circle
			drawCircle(row,column,day + 1,canvas);
			if(dayString.equals(mSelDay+"")){
				mPaint.setColor(mSelectDayColor);
			}else if(dayString.equals(mCurrDay+"") && mCurrDay != mSelDay && mCurrMonth == mSelMonth){

				//same month, different date than today, then today is red
				mPaint.setColor(mCurrentColor);
			}else{
				mPaint.setColor(mDayColor);
			}
			canvas.drawText(dayString, startX, startY, mPaint);
			if(tv_date != null){
				tv_date.setText(mSelYear + "／ " + (mSelMonth + 1));
			}
			
			if(tv_week != null){
				tv_week.setText(" week " + weekRow);
			}
		}
	}
	
	private void drawCircle(int row,int column,int day,Canvas canvas){
		if(daysHasThingList != null && daysHasThingList.size() >0){
			if(!daysHasThingList.contains(day))return;
			mPaint.setColor(mCircleColor);
			float circleX = (float) (mColumnSize * column +	mColumnSize*0.8);
			float circley = (float) (mRowSize * row + mRowSize*0.2);
			canvas.drawCircle(circleX, circley, mCircleRadius, mPaint);
		}
	}
	@Override
	public boolean performClick() {
		return super.performClick();
	}

	private int downX = 0,downY = 0;
	@Override
	public boolean onTouchEvent(MotionEvent event) {
		int eventCode=  event.getAction();
		switch(eventCode){
		case MotionEvent.ACTION_DOWN:
			downX = (int) event.getX();
			downY = (int) event.getY();
			break;
		case MotionEvent.ACTION_MOVE:
			break;
		case MotionEvent.ACTION_UP:
			int upX = (int) event.getX();
			int upY = (int) event.getY();
			if(Math.abs(upX-downX) < 10 && Math.abs(upY - downY) < 10){//click on the event
				performClick();
				doClickAction((upX + downX)/2,(upY + downY)/2);
			}
			break;
		}
		return true;
	}

	/**
	 * get initial size
	 */
	private void initSize(){
		mColumnSize = getWidth() / NUM_COLUMNS;
		mRowSize = getHeight() / NUM_ROWS;
	}
	
	/**
	 * set year and month
	 * @param year
	 * @param month
	 */
	private void setSelectYearMonth(int year,int month,int day){
		mSelYear = year;
		mSelMonth = month;
		mSelDay = day;
	}
	/**
	 * set the date as selected and perform the event
	 * @param x
	 * @param y
	 */
	private void doClickAction(int x,int y){
		int row = y / mRowSize;
		int column = x / mColumnSize;
		setSelectYearMonth(mSelYear,mSelMonth,daysString[row][column]);
		invalidate();
		//perform the click action
		if(dateClick != null){
			dateClick.onClickOnDate();
		}
	}

	/**
	 * click left, calendar flip backward
	 */
	public void onLeftClick(){
		int year = mSelYear;
		int month = mSelMonth;
		int day = mSelDay;
		if(month == 0){//if is january, change to december
			year = mSelYear-1;
			month = 11;
		}else if(DateUtils.getMonthDays(year, month) == day){

			// if the current date is the last date of the month, change the selected date
			// when moving forward.
			month = month-1;
			day = DateUtils.getMonthDays(year, month);
		}else{
			month = month-1;
		}
		setSelectYearMonth(year,month,day);
		invalidate();
	}
	
	/**
	 *click right , calendar flip forward
	 */
	public void onRightClick(){
		int year = mSelYear;
		int month = mSelMonth;
		int day = mSelDay;
		if(month == 11){//if the month is 12, change to 1
			year = mSelYear+1;
			month = 0;
		}else if(DateUtils.getMonthDays(year, month) == day){

			//if the current date is the last date of the month, change the selected date when
			//change the page to the calendar.
			month = month + 1;
			day = DateUtils.getMonthDays(year, month);
		}else{
			month = month + 1;
		}
		setSelectYearMonth(year,month,day);
		invalidate();
	}
	
	/**
	 * get selected year
	 * @return
	 */
	public int getmSelYear() {
		return mSelYear;
	}
	/**
	 * get selected month
	 * @return
	 */
	public int getmSelMonth() {
		return mSelMonth;
	}
	/**
	 * get selected day
	 * @param mSelDay
	 */
	public int getmSelDay() {
		return this.mSelDay;
	}
	/**
	 * dates color is black
	 * @param mDayColor
	 */
	public void setmDayColor(int mDayColor) {
		this.mDayColor = mDayColor;
	}
	
	/**
	 * set selected date color to white
	 * @param mSelectDayColor
	 */
	public void setmSelectDayColor(int mSelectDayColor) {
		this.mSelectDayColor = mSelectDayColor;
	}

	/**
	 *back ground of selected date is blue
	 * @param mSelectBGColor
	 */
	public void setmSelectBGColor(int mSelectBGColor) {
		this.mSelectBGColor = mSelectBGColor;
	}
	/**
	 * not selected, color is red
	 * @param mCurrentColor
	 */
	public void setmCurrentColor(int mCurrentColor) {
		this.mCurrentColor = mCurrentColor;
	}

	/**
	 * size of date
	 * @param mDaySize
	 */
	public void setmDaySize(int mDaySize) {
		this.mDaySize = mDaySize;
	}
	/**
	 * show current date
	 * @param tv_date
	 * 		show day
	 * @param tv_week
	 * 		show week
	 */
	public void setTextView(TextView tv_date,TextView tv_week){
		this.tv_date = tv_date;
		this.tv_week = tv_week;
		invalidate();
	}

	/**
	 * set how may days has events
	 * @param daysHasThingList
	 */
	public void setDaysHasThingList(List<Integer> daysHasThingList) {
		this.daysHasThingList = daysHasThingList;
	}

	/***
	 * set radius
	 * @param mCircleRadius
	 */
	public void setmCircleRadius(int mCircleRadius) {
		this.mCircleRadius = mCircleRadius;
	}
	
	/**
	 * set radius color
	 * @param mCircleColor
	 */
	public void setmCircleColor(int mCircleColor) {
		this.mCircleColor = mCircleColor;
	}
	
	/**
	 * click on the date
	 * @author shiwei.deng
	 *
	 */
	public interface DateClick{
		public void onClickOnDate();
	}

	/**
	 * click on date
	 * @param dateClick
	 */
	public void setDateClick(DateClick dateClick) {
		this.dateClick = dateClick;
	}
	
	/**
	 * change back to today
	 */
	public void setTodayToView(){
		setSelectYearMonth(mCurrYear,mCurrMonth,mCurrDay);
		invalidate();
	}
}
