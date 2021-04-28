package com.atozfit.main;

import java.util.Date;

public class AtoZBPAttributes {

    private String patientName;

    public String getPatientName() {
        return patientName;
    }

    public void setPatientName(String patientName) {
        this.patientName = patientName;
    }

    public int getsNo() {
        return sNo;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        AtoZBPAttributes that = (AtoZBPAttributes) o;

        if (sNo != that.sNo) return false;
        if (patientName != null ? !patientName.equals(that.patientName) : that.patientName != null)
            return false;
        if (systolic != null ? !systolic.equals(that.systolic) : that.systolic != null)
            return false;
        if (diastolic != null ? !diastolic.equals(that.diastolic) : that.diastolic != null)
            return false;
        if (date != null ? !date.equals(that.date) : that.date != null) return false;
        return year != null ? year.equals(that.year) : that.year == null;
    }

    @Override
    public int hashCode() {
        int result = patientName != null ? patientName.hashCode() : 0;
        result = 31 * result + sNo;
        result = 31 * result + (systolic != null ? systolic.hashCode() : 0);
        result = 31 * result + (diastolic != null ? diastolic.hashCode() : 0);
        result = 31 * result + (date != null ? date.hashCode() : 0);
        result = 31 * result + (year != null ? year.hashCode() : 0);
        return result;
    }

    public void setsNo(int sNo) {
        this.sNo = sNo;
    }

    public String getSystolic() {
        return systolic;
    }

    public void setSystolic(String systolic) {
        this.systolic = systolic;
    }

    public String getDiastolic() {
        return diastolic;
    }

    public void setDiastolic(String diastolic) {
        this.diastolic = diastolic;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    private int sNo;

    private String systolic;

    private String diastolic;

    private String date;

    private String year;

    @Override
    public String toString() {
        return "AtoZBPAttributes{" +
                "patientName='" + patientName + '\'' +
                ", sNo=" + sNo +
                ", systolic='" + systolic + '\'' +
                ", diastolic='" + diastolic + '\'' +
                ", date='" + date + '\'' +
                ", year='" + year + '\'' +
                '}';
    }
}
