package com.icmen.stepcounter.ui.stepcounter

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.fitness.Fitness
import com.google.android.gms.fitness.FitnessOptions
import com.google.android.gms.fitness.data.DataType
import com.icmen.stepcounter.R
import com.icmen.stepcounter.databinding.FragmentStepCounterBinding
import com.icmen.stepcounter.ui.common.BaseFragment

class StepCounterFragment: BaseFragment<FragmentStepCounterBinding, StepCounterViewModel>(){

    private val TAG = "STEP_COUNTER"
    private val fitnessRequestCode = 1234
    private val fitnessOptions: FitnessOptions by lazy {
        FitnessOptions.builder()
            .addDataType(DataType.TYPE_STEP_COUNT_DELTA, FitnessOptions.ACCESS_READ)
            .addDataType(DataType.AGGREGATE_STEP_COUNT_DELTA, FitnessOptions.ACCESS_READ)
            .build()
    }

    override fun setViewModelClass() = StepCounterViewModel::class.java
    override fun setViewBinding(): FragmentStepCounterBinding =
        FragmentStepCounterBinding.inflate(layoutInflater)

    override fun initView(savedInstanceState: Bundle?) {
        super.initView(savedInstanceState)
        getViewBinding()?.let { binding ->
            binding.bottomNavigationView.setOnItemSelectedListener {
                val type = when(it.itemId){
                    R.id.daily -> StepCountType.DAILY
                    R.id.weekly -> StepCountType.WEEKLY
                    R.id.monthly -> StepCountType.MONTHLY
                    else -> StepCountType.YEARLY
                }
                readFitnessData(type)
                true
            }
        }
    }
    override fun initLogic() {
        super.initLogic()
        if (hasFitnessPermission()) {
            readFitnessData(type = StepCountType.DAILY)
        } else {
            requestFitnessPermission()
        }
    }

    override fun initObservers() {
        super.initObservers()
        getViewModel()?.stepCountLiveData?.observe(this) {
            getViewBinding()?.stepCountTextView?.text = it.stepInfo
            getViewBinding()?.stepCountRangeTextView?.text = it.rangeInfo
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        when (resultCode) {
            AppCompatActivity.RESULT_OK -> readFitnessData(type = StepCountType.DAILY)
            else -> Log.e(TAG, "There was an error signing into Fit. requestCode: $requestCode requestCode: $resultCode")
        }
    }

    private fun readFitnessData(type: StepCountType) {
        getViewModel()?.let {
            Fitness.getHistoryClient(requireActivity(), getGoogleAccount())
                .readData(it.getFitnessDataQuery(type))
                .addOnSuccessListener { dataReadResponse ->
                    it.parseFitnessData(dataReadResponse)
                }
                .addOnFailureListener { e ->
                    Log.e(TAG, "There was a problem reading the data.", e)
                }
        }
    }

    private fun requestFitnessPermission(){
        GoogleSignIn.requestPermissions(
            this,
            fitnessRequestCode,
            getGoogleAccount(),
            fitnessOptions
        )
    }
    private fun hasFitnessPermission(): Boolean = GoogleSignIn.hasPermissions(getGoogleAccount(), fitnessOptions)

    private fun getGoogleAccount() = GoogleSignIn.getAccountForExtension(requireActivity(), fitnessOptions)

}