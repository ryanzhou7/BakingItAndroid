package com.example.ryanzhouold.bakingandroid.ui.step;

import com.example.ryanzhouold.bakingandroid.data.dto.StepDto;
import com.example.ryanzhouold.bakingandroid.ui.base.BasePresenter;

import java.util.List;

public class StepPresenter<V extends StepContract.View> extends BasePresenter<V>
        implements StepContract.Presenter<V>{
    private StepContract.View mView;
    private List<StepDto> mStepDtos;

    public StepPresenter(StepContract.View view, List<StepDto> stepDtos){
        mView = view;
        mStepDtos = stepDtos;
    }

    @Override
    public void prevStepFrom(int pos) {
        if(pos<=0){
            mView.showMessage("You are at the first step");
        }
        else{
            mView.showStep(mStepDtos.get(pos-1));
        }
    }

    @Override
    public void nextStepFrom(int pos) {
        if(pos>= mStepDtos.size()){
            mView.showMessage("You are at the last step");
        }
        else{
            mView.showStep(mStepDtos.get(pos+1));
        }
    }

}
