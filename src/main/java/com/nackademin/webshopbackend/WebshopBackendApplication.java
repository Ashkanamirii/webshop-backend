package com.nackademin.webshopbackend;

import com.nackademin.webshopbackend.models.Address;
import com.nackademin.webshopbackend.models.Category;
import com.nackademin.webshopbackend.models.Product;
import com.nackademin.webshopbackend.repos.AddressDAO;
import com.nackademin.webshopbackend.repos.CategoryDAO;
import com.nackademin.webshopbackend.repos.ProductDAO;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class WebshopBackendApplication {

    public static void main(String[] args) {
        SpringApplication.run(WebshopBackendApplication.class, args);
    }

    //Fyller databasen före start med en huvudstad och ett land
//    @Bean   //Denna metod kommer bara att köras om den har en @Bean-annotering
//    public CommandLineRunner addProducts(ProductDAO productDAO) {
//        return (args) -> {
//            List<Category> categories;
//
//            List<Product> prodList = new ArrayList<>();
//            List<Category> catList = List.of(new Category("skafferi"),new Category("snacks-godis"));
//            prodList.add(new Product("Havegryn",
//                    "Nyckelhålsmärkta havregryn. Rika på fibrer, vitaminer och mineraler. Perfekta till bakningen eller gröten"
//                    , 12, "750g"
//                    , "AXA", "https://cdn4.matsmart.se/sites/se/files/styles/product_zoom/public/products/axa_havregryn_750g_ny.jpg?itok=pOCVoEG4"
//                    , 5, true,catList));
//
//            catList = List.of(new Category("skafferi"),new Category("mejeri"));
//            prodList.add(new Product( "Vitlökssås",
//                    "Vitlöksås som är tillagad i Blekinge med mild svensk rapsolja, svenska ägg från frigående höns och finhackad persilja. Passar utmärkt till grillat, fisk, skaldjur, kebab och falafel."
//                    , 15, "370ml"
//                    , "Felix", "https://cdn4.matsmart.se/sites/se/files/styles/product_zoom/public/products/_vitloksdressing_370ml_felix.jpg?itok=vTffnSqz"
//                    , 50, false,catList));
//
//            catList = List.of(new Category("skafferi"),new Category("snacks-godis"));
//            prodList.add(new Product( "Earl Gray",
//                    "Life by Follis är Tekompaniets eget varumärke med kontroll över hela tillverkningen från teplanta till kopp. Life by Follis producerar te av premiumkvalitet med spännande smaker från noggrant utvalda råvaror. Teet växer högt upp i bergen i Indien och kommer från ett och samma plantage, så kallat Single Estaste. Life by Follis är både ekologiskt och Fairtraidecertifierat."
//                    , 29, "40g"
//                    , "Life by follis", "https://cdn4.matsmart.se/sites/se/files/styles/product_zoom/public/products/ms121320-lif_earl_gray_40g_ekojpg.jpg?itok=F5ErYdJU"
//                    , 7, false,catList));
//
//            catList = List.of(new Category("skafferi"),new Category("snacks-godis"));
//            prodList.add(new Product( "Bryggkaffe Kasenga",
//                    "Kasenga är ett mörkrostat kaffe bestående av 100 procent noga utvalda och ekologiska arabicabönor. Malningen passar utmärkt för din vanliga hemmabryggare."
//                    , 79, "450g"
//                    , "Rutasoka", "https://cdn1.matsmart.se/sites/se/files/styles/product_zoom/public/products/rutasoka_kasenga_bryggmalet_ny.jpg?itok=ZZFlI5s-"
//                    , 3, false,catList));
//
//            catList = List.of(new Category("skafferi"),new Category("snacks-godis"));
//            prodList.add(new Product( "Happy Cherries",
//                    "2475g godis i form av en körsbär! Mums!"
//                    , 69, "2475g"
//                    , "Haribo", "https://cdn4.matsmart.se/sites/se/files/styles/product_zoom/public/products/ms121632-happy_cherriesjpg.jpg?itok=oNJevPCy"
//                    , 10, false,catList));
//
//            catList = List.of(new Category("skafferi"),new Category("snacks-godis"));
//            prodList.add(new Product( "Valnötter",
//                    "Valnötter från WH."
//                    , 39, "250g"
//                    , "WH", "https://cdn4.matsmart.se/sites/se/files/styles/product_zoom/public/products/wh_valnotter_250g_.jpg?itok=XqgcKtiU"
//                    , 5, false,catList));
//
//            catList = List.of(new Category("skafferi"),new Category("snacks-godis"));
//            prodList.add(new Product("Sköljmedel White Flower",
//                    "Sköljmedel för känslig hud med ingredienser från hållbara källor och naturligt ursprung. Formulan är också biolgiskt nedbrytbar. Dermatologiskt testad."
//                    , 19, "650ml"
//                    , "Softlan", "https://cdn4.matsmart.se/sites/se/files/styles/product_zoom/public/products/ms120252_sof_softlan_skoljmedel_plant_based_white_flower_650_ml_650mljpg.jpg?itok=IWVl9UAE"
//                    , 0, false,catList));
//
//        };
//        }
//
//    //Fyller databasen före start med en huvudstad och ett land
//    @Bean   //Denna metod kommer bara att köras om den har en @Bean-annotering
//    public CommandLineRunner addCategories(CategoryDAO categoryRepo) {
//        return (args) -> {
//            categoryRepo.save(new Category("Skafferi"));
//            categoryRepo.save(new Category("Mejeri"));
//            categoryRepo.save(new Category("Snacks och godis"));
//            categoryRepo.save(new Category("Hem och städ"));
//            categoryRepo.save(new Category("Frukt och grönt"));
//            categoryRepo.save(new Category("Husdjur"));
//            categoryRepo.save(new Category("Barnmat"));
//        };
//    }
//
//        //Fyller databasen före start med en huvudstad och ett land
//        @Bean   //Denna metod kommer bara att köras om den har en @Bean-annotering
//        public CommandLineRunner addAddresses(AddressDAO addressRepo) {
//            return (args) -> {
//                addressRepo.save(new Address("Vintergatan 12", "16923", "Mars"));
//                addressRepo.save(new Address("Vintergatan 52", "16923", "Mars"));
//                addressRepo.save(new Address("Galaxvägen 122", "56623", "Jupiter"));
//                addressRepo.save(new Address("Galaxvägen 622", "56623", "Jupiter"));
//                addressRepo.save(new Address("Galaxvägen 622", "56623", "Jupiter"));
//                addressRepo.save(new Address("Galaxvägen 622", "56623", "Jupiter"));
//                addressRepo.save(new Address("Galaxvägen 622", "56623", "Jupiter"));
//                addressRepo.save(new Address("Galaxvägen 622", "56623", "Jupiter"));
//            };
//
//}
//


}
