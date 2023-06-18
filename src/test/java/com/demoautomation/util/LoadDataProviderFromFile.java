package com.demoautomation.util;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;
import org.testng.annotations.DataProvider;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LoadDataProviderFromFile {
    @DataProvider(name="fillFormDataProvider")
    public static Object[][] fillFormDataProvider() {
        return getDataFromFile("src/test/resources/dataSets/checkoutDataSet.json");
    }

    @DataProvider(name="fillSearchBar")
    public static Object[][] fillSearchBar() {
        return getDataFromFile("src/test/resources/dataSets/searchDataSet.json");
    }


    private static Object[][] getDataFromFile(String path) {
        JsonElement jsonData = null;
        try {
            jsonData = new JsonParser().parse(new FileReader(path));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        JsonElement dataSet = jsonData.getAsJsonObject().get("data");
        List<Map<?,?>> testData = new Gson().fromJson(dataSet, new TypeToken<List<Map<?,?>>>() {
        }.getType());
        Object[][] returnValue = new Object[testData.size()][1];
        int index = 0;
        for (Object[] each : returnValue) {
            each[0] = testData.get(index++);
        }
        return returnValue;
    }
}