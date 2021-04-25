package com.atozfit.dto;

import android.util.Log;

import com.atozfit.Service.AtoZBPService;
import com.atozfit.main.AtoZBPAttributes;

import java.util.ArrayList;
import java.util.List;

public class AtoZBPRepoImpl implements AtoZBPRepo {

    private static final String TAG = "AtoZBPRepoImpl";


    private AtoZBPService atozBPService;

    @Override
    public void saveData(List<AtoZBPAttributes> bpAttributes) {
        Log.d(TAG,"Inside the AtoZBPRepoImpl saveData method");
        atozBPService.saveData(bpAttributes);
        Log.d(TAG,"Outside the AtoZBPRepoImpl saveData method");
    }

    @Override
    public List<AtoZBPAttributes> retrieveEntireData() {
        List<AtoZBPAttributes> bpListAttr= new ArrayList<>();
        Log.d(TAG,"Inside the AtoZBPRepoImpl retrieveEntireData method");
        bpListAttr=atozBPService.retrieveBPData();
        Log.d(TAG,"Outside the AtoZBPRepoImpl retrieveEntireData method");
        return bpListAttr;
    }

    @Override
    public List<AtoZBPAttributes> retrieveBasedOnYear(String year) {
        return null;
    }
}
