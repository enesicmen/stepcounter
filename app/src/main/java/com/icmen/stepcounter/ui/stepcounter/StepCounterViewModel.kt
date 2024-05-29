package com.icmen.stepcounter.ui.stepcounter

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.android.gms.fitness.data.DataSet
import com.google.android.gms.fitness.data.DataType
import com.google.android.gms.fitness.request.DataReadRequest
import com.google.android.gms.fitness.result.DataReadResponse
import com.icmen.stepcounter.data.Constants
import com.icmen.stepcounter.data.model.FitnessHistory
import dagger.hilt.android.lifecycle.HiltViewModel
import java.text.DateFormat
import java.util.Calendar
import java.util.Date
import java.util.TimeZone
import java.util.concurrent.TimeUnit
import javax.inject.Inject

enum class StepCountType(val calendarType: Int) {
    DAILY(Calendar.DAY_OF_WEEK),
    WEEKLY(Calendar.WEEK_OF_YEAR),
    MONTHLY(Calendar.MONTH),
    YEARLY(Calendar.YEAR);
}
@HiltViewModel
class StepCounterViewModel @Inject constructor(): ViewModel(){

    private val dateFormat = DateFormat.getDateInstance()
    private val _stepCountLiveData = MutableLiveData<FitnessHistory>()
    val stepCountLiveData: LiveData<FitnessHistory> = _stepCountLiveData

    private var startTime = Date().time
    private var endTime = Date().time

    fun getFitnessDataQuery(type: StepCountType): DataReadRequest {
        val calendar = Calendar.getInstance(TimeZone.getTimeZone("UTC"))
        val now = Date()
        calendar.time = now
        endTime = calendar.timeInMillis
        calendar.add(type.calendarType, -1)
        startTime = calendar.timeInMillis

        return DataReadRequest.Builder()
            // The data request can specify multiple data types to return, effectively
            // combining multiple data queries into one call.
            // In this example, it's very unlikely that the request is for several hundred
            // datapoints each consisting of a few steps and a timestamp.  The more likely
            // scenario is wanting to see how many steps were walked per day, for 7 days.
            .aggregate(DataType.TYPE_STEP_COUNT_DELTA, DataType.AGGREGATE_STEP_COUNT_DELTA)
            // Analogous to a "Group By" in SQL, defines how data should be aggregated.
            // bucketByTime allows for a time span, whereas bucketBySession would allow
            // bucketing by "sessions", which would need to be defined in code.
            .bucketByTime(1, TimeUnit.DAYS)
            .setTimeRange(startTime, endTime, TimeUnit.MILLISECONDS)
            .build()
    }

    fun parseFitnessData(fitnessResponse: DataReadResponse){
        if (fitnessResponse.buckets.isNotEmpty()) {
            for (bucket in fitnessResponse.buckets) {
                bucket.dataSets.forEach {
                    dumpDataSet(it)
                }
            }
        } else if (fitnessResponse.dataSets.isNotEmpty()) {
            fitnessResponse.dataSets.forEach {
                dumpDataSet(it)
            }
        }
    }

    private fun dumpDataSet(dataSet: DataSet) {
        for (dp in dataSet.dataPoints) {
            dp.dataType.fields.forEach {
                if (it.name == Constants.Fitness.STEP_COUNTS_FIELD_NAME){
                    _stepCountLiveData.value = FitnessHistory(
                        stepInfo = "${dp.getValue(it)} ${it.name}",
                        rangeInfo = "${dateFormat.format(startTime)} and ${dateFormat.format(endTime)}",
                    )
                }
            }
        }
    }
}