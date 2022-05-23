package com.moyo.kinandcarta;

import com.moyo.kinandcarta.Models.ProductModel;
import com.moyo.kinandcarta.Models.promos.Promotion;
import com.moyo.kinandcarta.Models.promos.ThreshHoldPromotionModel;
import com.moyo.kinandcarta.Models.promos.TwoOrMorePromotionModel;
import com.moyo.kinandcarta.config.Template;
import com.moyo.kinandcarta.repo.ProductRepo;
import com.moyo.kinandcarta.service.CheckOutService;
import com.moyo.kinandcarta.service.impl.CheckOutServiceImpl;
import lombok.Setter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.util.FileCopyUtils;

import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.LogManager;

@SpringBootApplication
@EnableJpaRepositories
@Setter
public class KinAndCartaApplication implements CommandLineRunner {

    private static final Logger LOGGER= LoggerFactory.getLogger(KinAndCartaApplication.class);

    @Autowired
    private Template template;

    @Autowired
    private ProductRepo productRepo;

    BigDecimal Total;


    ArrayList<Promotion> promos=new ArrayList<>();

    public static void main(String[] args) {
        if(args.length<1){
            printUsage();
            System.exit(1);
        }
        SpringApplication.run(KinAndCartaApplication.class, args);
    }

    private static void printUsage() {
        Resource resource = new ClassPathResource("Help/CheckOutUsage.txt");
        try{
            InputStream inputStream=resource.getInputStream();
            byte[] bdata= FileCopyUtils.copyToByteArray(inputStream);
            String data= new String(bdata, StandardCharsets.UTF_8);
            System.out.println(data);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run(String... args) throws Exception {
        LOGGER.info("Running Check out application...");
        prepApp();
        prepPromotions();

        CheckOutService checkOutService= new CheckOutServiceImpl(promos);
        ArrayList<ProductModel> res= new ArrayList<>();
        for(Long i:template.getItems()){
            res.add(productRepo.findById(i).get());
        }


        checkOutService.scanItems(res);

       Total=checkOutService.getTotalPrice();
       System.out.println(Total);

    }

    private void prepApp(){
        ArrayList<ProductModel> list= new ArrayList<>();
        ProductModel p1=new ProductModel(1L,"Water Bottle",BigDecimal.valueOf(24.95));
        ProductModel p2=new ProductModel(2L,"Hoodie",BigDecimal.valueOf(65.00));
        ProductModel p3=new ProductModel(3L,"Sticker Set",BigDecimal.valueOf(3.99));
        list.add(p1);
        list.add(p2);
        list.add(p3);
        productRepo.saveAll(list);
    }

    private void prepPromotions(){
        Promotion  promotion1=new TwoOrMorePromotionModel();
        Promotion promotion2= new ThreshHoldPromotionModel();
        promos.add(promotion1);
        promos.add(promotion2);
    }


}
