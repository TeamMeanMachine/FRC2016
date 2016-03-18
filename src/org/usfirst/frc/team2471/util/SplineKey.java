package org.usfirst.frc.team2471.util;

import java.awt.geom.Point2D;

public class SplineKey {
	
	double m_time;
	private double m_value;
	Point2D m_inTangent;
	Point2D m_outTangent;		
	SplineKey m_next, m_prev;
	SplineCurve m_motionCurve;
	boolean m_unify;
	double m_angle;
	double m_weight;
	boolean m_tangentsDirty;
	
	enum SlopeMethod
	{
		SLOPE_SMOOTH, SLOPE_LINEAR, SLOPE_MANUAL, SLOPE_FLAT, SLOPE_CLAMPED, SLOPE_PLATEAU, SLOPE_STEPPED, SLOPE_STEPPEDNEXT
	}
	SlopeMethod slopeMethod;

	SplineKey() {
	}

	SplineKey GetNextKey() {
		return m_next;
	}
	SplineKey GetPrevKey() { 
		return m_prev;
	}
	void InsertBefore(SplineKey key) {
	}
	void InsertAfter(SplineKey key) {
	}
	double GetTime() {
		return m_time;
	}
	void SetTime( double time ) {
		m_time = time;
	}
	double getValue() {
		return m_value;
	}
	void setValue(double m_value) {
		this.m_value = m_value;
	}
	SplineCurve GetMotionCurve() {
		return m_motionCurve;
	}
	void SetMotionCurve( SplineCurve motionCurve ) {
		m_motionCurve = motionCurve;
	}
	
	class BiasInfo
	{
		SlopeMethod outSlopeMethod;
		double m_outAngle;
		double m_outWeight;

		BiasInfo( SplineKey key ) {
		}
	}
	
	BiasInfo biasInfo;  // only create one of these if the spline is not unified at this point
	
	BiasInfo GetBiasInfo() {
	}

	float GetInAngle() {
		
	}
	float GetInWeight() {
		
	}
	float GetOutAngle() {
		
	}
	float GetOutWeight() {
		
	}

	void SetInAngle( float fAAngleRadians ) { // using any of these will set interpolation method to manual (and cost memory)
	
	}
	void SetInWeight( float fWeight ) {
		
	}
	void SetOutAngle( float fAngleRadians ) {
		
	}
	void SetOutWeight( float fWeight ) {
		
	}
	void SetInTangentBiasProperties( Point2D v2Tangent, boolean bWeightOnly ) {
		
	}
	void SetOutTangentBiasProperties( Point2D v2Tangent, boolean bWeightOnly ) {
		
	}

	Point2D GetInTangent() {
		
	}
	Point2D GetOutTangent() {
		
	}

	SlopeMethod GetInSlopeMethod() {
		
	}
	void SetInSlopeMethod( SlopeMethod slopeMethod ) {
		
	}
	SlopeMethod GetOutSlopeMethod() {
		
	}
	void SetOutSlopeMethod( SlopeMethod slopeMethod ) {
		
	}

	boolean IsTangentsDirty() {
		return m_tangentsDirty;
	}
	void SetTangentsDirtyFlag( boolean bFlag ) {
		m_tangentsDirty = bFlag;
	}
	void CalcTangents() {
		
	}
}
