package com.nackademin.webshopbackend.repos;

import com.nackademin.webshopbackend.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by Tomas Dahlander <br>
 * Date: 2021-04-09 <br>
 * Time: 16:30 <br>
 * Project: webshop-back-end <br>
 */
@Repository
public interface ProductDAO extends JpaRepository<Product, Integer> {



  /*  List<Category> categories;

    public ProductDAO(){
        CategoryDAO categoryDAO = new CategoryDAO();
        categories = categoryDAO.getAllCategories();
    }

    public List<Product> getAllProducts(){
        List<Product> list = new ArrayList<>();
        list.add(new Product(1,"Havegryn",
            "Nyckelhålsmärkta havregryn. Rika på fibrer, vitaminer och mineraler. Perfekta till bakningen eller gröten"
            ,12,"750g"
            ,"AXA","https://cdn4.matsmart.se/sites/se/files/styles/product_zoom/public/products/axa_havregryn_750g_ny.jpg?itok=pOCVoEG4"
            ,5,true,categories.get(0)));

        list.add(new Product(2,"Vitlökssås",
             "Vitlöksås som är tillagad i Blekinge med mild svensk rapsolja, svenska ägg från frigående höns och finhackad persilja. Passar utmärkt till grillat, fisk, skaldjur, kebab och falafel."
            ,15,"370ml"
            ,"Felix","https://cdn4.matsmart.se/sites/se/files/styles/product_zoom/public/products/_vitloksdressing_370ml_felix.jpg?itok=vTffnSqz"
            ,50,false,categories.get(0)));

        list.add(new Product(3,"Earl Gray",
            "Life by Follis är Tekompaniets eget varumärke med kontroll över hela tillverkningen från teplanta till kopp. Life by Follis producerar te av premiumkvalitet med spännande smaker från noggrant utvalda råvaror. Teet växer högt upp i bergen i Indien och kommer från ett och samma plantage, så kallat Single Estaste. Life by Follis är både ekologiskt och Fairtraidecertifierat."
            ,29,"40g"
            ,"Life by follis","https://cdn4.matsmart.se/sites/se/files/styles/product_zoom/public/products/ms121320-lif_earl_gray_40g_ekojpg.jpg?itok=F5ErYdJU"
            ,7,false,categories.get(0)));

        list.add(new Product(4,"Bryggkaffe Kasenga",
            "Kasenga är ett mörkrostat kaffe bestående av 100 procent noga utvalda och ekologiska arabicabönor. Malningen passar utmärkt för din vanliga hemmabryggare."
            ,79,"450g"
            ,"Rutasoka","https://cdn1.matsmart.se/sites/se/files/styles/product_zoom/public/products/rutasoka_kasenga_bryggmalet_ny.jpg?itok=ZZFlI5s-"
            ,3,false,categories.get(0)));

        list.add(new Product(5,"Happy Cherries",
            "2475g godis i form av en körsbär! Mums!"
            ,69,"2475g"
            ,"Haribo","https://cdn4.matsmart.se/sites/se/files/styles/product_zoom/public/products/ms121632-happy_cherriesjpg.jpg?itok=oNJevPCy"
            ,10,false,categories.get(1)));

        list.add(new Product(6,"Valnötter",
            "Valnötter från WH."
            ,39,"250g"
            ,"WH","https://cdn4.matsmart.se/sites/se/files/styles/product_zoom/public/products/wh_valnotter_250g_.jpg?itok=XqgcKtiU"
            ,5,false,categories.get(1)));

        list.add(new Product(7,"Sköljmedel White Flower",
            "Sköljmedel för känslig hud med ingredienser från hållbara källor och naturligt ursprung. Formulan är också biolgiskt nedbrytbar. Dermatologiskt testad."
            ,19,"650ml"
            ,"Softlan","https://cdn4.matsmart.se/sites/se/files/styles/product_zoom/public/products/ms120252_sof_softlan_skoljmedel_plant_based_white_flower_650_ml_650mljpg.jpg?itok=IWVl9UAE"
            ,0,false,categories.get(2)));
        return list;
    }*/
}
