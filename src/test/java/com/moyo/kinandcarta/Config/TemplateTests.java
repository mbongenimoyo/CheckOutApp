package com.moyo.kinandcarta.Config;


import com.moyo.kinandcarta.config.Template;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

public class TemplateTests {


    @Test
    public void Template_testGetItems(){
        Template template=new Template();
        ArrayList<Long> items=new ArrayList<>();
        items.add(12L);

        template.setItems(items);
        Assertions.assertEquals(12L,template.getItems().get(0));
    }
}
