package com.atozfit.dto;

import com.atozfit.main.AtoZBPAttributes;

import java.util.List;

public interface AtoZBPRepo {

    public void saveData(List<AtoZBPAttributes> bpAttributes);

    public List<AtoZBPAttributes>  retrieveEntireData();

    public List<AtoZBPAttributes> retrieveBasedOnYear(String year);

}
