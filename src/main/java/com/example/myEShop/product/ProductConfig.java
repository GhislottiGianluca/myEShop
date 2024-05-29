package com.example.myEShop.product;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import java.util.List;

@Configuration
@EnableJpaAuditing
public class ProductConfig {

    String baseImage = "https://www.dropbox.com/scl/fi/ozh335x54aov3b2vlpej9/base_image.png?rlkey=rury7lnqs27ouz0xww3iw6aud&st=ldrqd9fm&raw=1";
//    @Bean
//    CommandLineRunner commandLineRunnerProduct(ProductRepository productRepository) {
//        return args -> {
//            Product cup1 = new ProductBuilder("12oz White Ceramic Mug Go Away I'm Coding Coder Programmer")
//                    .description("Meaning: For a programmer who needs to focus on coding all day, this funny mug with the quote \"Go Away, I'm Coding\" will be their next favorite companion at work or in the office. They can place it next to their work table and people will get the message they can't say out loud. Sarcastic but fun mug to take a break from programming and remind them that there's more to life than codes and algorithms." +
//                            "\nDishwasher and Microwave Safe: These great mugs can be washed straight in the dishwasher, all day and every day, which means they can save you time and be more hygienic. Perfect for your hot or cold drinks Easily reheat the coffee or tea you forgot to drink right away because it is microwave-safe, very convenient and perfect for your busy lifestyle.")
//                    .category(ProductCategories.CUP)
//                    .image1("https://www.dropbox.com/scl/fi/qyeecn5f7nb3tgkor32wx/img1.jpeg?rlkey=rvb3xcx1m9znz8m07r2ljfbu0&st=sy3s7jz9&raw=1")
//                    .image2("https://www.dropbox.com/scl/fi/0ukduhcvh0rm4xo4d4rj6/img2.jpeg?rlkey=wlrjedmm4kff38p3tmed5nf0m&st=er31ex88&raw=1")
//                    .image3("https://www.dropbox.com/scl/fi/6w13kb55sk8zro7j09tpm/img3.jpeg?rlkey=jn6hcnrgbzt2xtar569u0a1ic&st=fkf6likk&raw=1")
//                    .image4("https://www.dropbox.com/scl/fi/5971zm49yj9ka4epco2fa/img4.jpeg?rlkey=qb0psthcy4njbiosbqob0ipj5&st=vu53awne&raw=1")
//                    .image5("https://www.dropbox.com/scl/fi/vi17pi0o6tyit4uao6a2f/img5.jpeg?rlkey=9f0008gwy1jela9jd0ho4vnrn&st=b2aam4nq&raw=1")
//                    .price(14.08)
//                    .currency("CHF")
//                    .remained(150)
//                    .sold(10)
//                    .build();
//
//            Product cup2 = new ProductBuilder("99WakeUp Loading Future Software Engineer Coffee")
//                    .description("Microwave safe")
//                    .category(ProductCategories.CUP)
//                    .image1("https://www.dropbox.com/scl/fi/dv4k8sk3xrp1bwweb4y8b/img1.jpeg?rlkey=v97jeaxe47eg0d74crh94rqui&st=pa7ph1m0&raw=1")
//                    .image2(baseImage)
//                    .image3(baseImage)
//                    .image4(baseImage)
//                    .image5(baseImage)
//                    .price(14.08)
//                    .currency("CHF")
//                    .remained(150)
//                    .sold(18)
//                    .build();
//
//            Product cup3 = new ProductBuilder("Punkcards - Funny Coffee Mugs - \"Engineer Someone Who Does\"")
//                    .description("Printed on both sides This fun conversation starter makes a perfect gift. High Quality Ceramic Mug - Cheers to Caffeinated Laughs - Funny Coffee Mug! This is a perfect gift for any tea or coffee lover" +
//                            "\nStart your day with a smile - Get our funny coffee mug! Packaged in a break-proof mug box to ensure safe delivery to give as a fun and unique birthday gift.")
//                    .category(ProductCategories.CUP)
//                    .image1("https://www.dropbox.com/scl/fi/bo8wl63q88ettp09vpbq6/img1.jpeg?rlkey=qzv9szvt7duo9qety9ig77opf&st=gla5qksh&raw=1")
//                    .image2("https://www.dropbox.com/scl/fi/2dc4g26p9lcdnkv6eeywu/img2.jpeg?rlkey=su0zslmfj3f4iamkevz67lnhc&st=qdxifjic&raw=1")
//                    .image3("https://www.dropbox.com/scl/fi/6al41chcwcb9x5k85b2in/img3.jpeg?rlkey=mzr44nl7lzz6lg7qz3gy59yvd&st=1pngalqa&raw=1")
//                    .image4("https://www.dropbox.com/scl/fi/vhr70xnkqewnmu3uiv1ua/img4.jpeg?rlkey=i96s5tns4fnkd48277je4lkq7&st=lwu2wq64&raw=1")
//                    .image5("https://www.dropbox.com/scl/fi/94k44l5nqeli1hahxajip/img5.jpeg?rlkey=ofejveplsuusdidkpwlzlqk2q&st=v8h0y2xz&raw=1")
//                    .price(21.85)
//                    .currency("CHF")
//                    .remained(150)
//                    .sold(1)
//                    .build();
//
//            Product cup4 = new ProductBuilder("IT Code Programmer Coffee Mug")
//                    .description("Gift idea for IT enthusiasts, specialized technicians and software developers: all the nerds behind the PC who deal with code, IT and software. Ideal for Home Office developers." +
//                            "\nHigh Quality: Dishwasher and microwave safe. Capacity: about 330ml. 100% high quality ceramic. It can be used as coffee mug, cocoa cup and coffee mug.")
//                    .category(ProductCategories.CUP)
//                    .image1("https://www.dropbox.com/scl/fi/tmvdhimun5dch9tj2wp6e/img1.jpeg?rlkey=3dfpf02g3oitefo3gnqulifnp&st=p45x5ze2&raw=1")
//                    .image2("https://www.dropbox.com/scl/fi/k2t2j4jyv6syttsg3uuiq/img2.jpeg?rlkey=4k9gc8ae9vf7rsx1697phbazv&st=1n8rm54x&raw=1")
//                    .image3("https://www.dropbox.com/scl/fi/2i09ttm9neanr19qg89aj/img3.jpeg?rlkey=cd119keuwojwvnkks29h1tw8m&st=n6zuzzf7&raw=1")
//                    .image4("https://www.dropbox.com/scl/fi/z6h0gpjsh72ejw2ys4l7u/img4.jpeg?rlkey=qom91jvndplshjdrs4cc4o2s4&st=iq2whfwz&raw=1")
//                    .image5("https://www.dropbox.com/scl/fi/0m4b9g6ck0h2xwyv2n24p/img5.jpeg?rlkey=h54ztsf0rw1puuwbzq010se7m&st=032567jw&raw=1")
//                    .price(17.90)
//                    .currency("CHF")
//                    .remained(150)
//                    .sold(29)
//                    .build();
//
//            Product cup5 = new ProductBuilder("Trust Me I'm an Engineer Classic White Ceramic Mug 325ml")
//                    .description("\nFunny Engineer Coffee Mug - Dishwasher and Microwave Safe - Printed on Both Sides. It can be used for home and office.")
//                    .category(ProductCategories.CUP)
//                    .image1("https://www.dropbox.com/scl/fi/2tifwuoqirmhx6ako5sjz/img1.jpeg?rlkey=4fdyvoeg9g9kj061r4x73xq7j&st=qu5iod8i&raw=1")
//                    .image2("https://www.dropbox.com/scl/fi/v0r4xsm7r1wpvo3h3mkxm/img2.jpeg?rlkey=970ixspibcov7id3jzip1x5v0&st=lmlgy0ix&raw=1")
//                    .image3(baseImage)
//                    .image4(baseImage)
//                    .image5(baseImage)
//                    .price(16.95)
//                    .currency("CHF")
//                    .remained(150)
//                    .sold(3)
//                    .build();
//
//            Product cup6 = new ProductBuilder("MG3467 Go Away I'm Coding printed ceramic tea and coffee mug")
//                    .description("100% Dishwasher and Microwave safe. " +
//                            "\nHigh quality prints using the dye sublimation method.")
//                    .category(ProductCategories.CUP)
//                    .image1("https://www.dropbox.com/scl/fi/ex11jl4jjr7q8z5xns7oc/img1.jpeg?rlkey=197edbx4qabfvgea86ugfcs17&st=oq9zhbjq&raw=1")
//                    .image2(baseImage)
//                    .image3(baseImage)
//                    .image4(baseImage)
//                    .image5(baseImage)
//                    .price(12.85)
//                    .currency("CHF")
//                    .remained(150)
//                    .sold(5)
//                    .build();
//
//            Product cup7 = new ProductBuilder("Looking For Commitment - White Ceramic Mug")
//                    .description("Sturdy ceramic construction\n" +
//                            "Suitable for hot and cold drinks")
//                    .category(ProductCategories.CUP)
//                    .image1("https://www.dropbox.com/scl/fi/sis3vhjz68xn8hs29ch6b/img1.jpeg?rlkey=3btx397vgi99916cspubtiqnp&st=jufsi57e&raw=1")
//                    .image2(baseImage)
//                    .image3(baseImage)
//                    .image4(baseImage)
//                    .image5(baseImage)
//                    .price(17.79)
//                    .currency("CHF")
//                    .remained(150)
//                    .sold(30)
//                    .build();
//
//            Product cup8 = new ProductBuilder("Github Contributions - White Ceramic Mug")
//                    .description("Sturdy ceramic construction\n" +
//                            "Suitable for hot and cold drinks")
//                    .category(ProductCategories.CUP)
//                    .image1("https://www.dropbox.com/scl/fi/ptki608x7gcn6fiihve3r/img1.jpeg?rlkey=ssp0ps7scdwa6deonqndxmqyl&st=o3vmdu5h&raw=1")
//                    .image2(baseImage)
//                    .image3(baseImage)
//                    .image4(baseImage)
//                    .image5(baseImage)
//                    .price(17.79)
//                    .currency("CHF")
//                    .remained(150)
//                    .sold(32)
//                    .build();
//
//            Product cup9 = new ProductBuilder("Limited \"Facebook like in the shape of a mug")
//                    .description("It seems like everyone is posting who online they are up to these days and now even your cup of tea is always up to it.\n" +
//                            "A unique mug that will definitely stand out and a great gift for techie people.")
//                    .category(ProductCategories.CUP)
//                    .image1("https://www.dropbox.com/scl/fi/canv0ogwcf41kyejv139w/img1.jpeg?rlkey=2omc8mkafvlhs42gefcku1wv3&st=ua2vkhvz&raw=1")
//                    .image2(baseImage)
//                    .image3(baseImage)
//                    .image4(baseImage)
//                    .image5(baseImage)
//                    .price(18.16)
//                    .currency("CHF")
//                    .remained(150)
//                    .sold(7)
//                    .build();
//
//            Product cup10= new ProductBuilder("I Don't Need Google My Wife Knows Everything - High Quality Mug")
//                    .description("High quality ceramic coffee mug\n" +
//                            "Design is on both sides of the mug\n" +
//                            "Dishwasher and microwave safe\n" +
//                            "Capacity of 312ml")
//                    .category(ProductCategories.CUP)
//                    .image1("https://www.dropbox.com/scl/fi/nfk7mucjd4n6o2sw3zhsx/img1.jpeg?rlkey=3mclzh57845dit71t8dzw3qor&st=78ywu0hb&raw=1")
//                    .image2(baseImage)
//                    .image3(baseImage)
//                    .image4(baseImage)
//                    .image5(baseImage)
//                    .price(11.19)
//                    .currency("CHF")
//                    .remained(150)
//                    .sold(50)
//                    .build();
//
//            Product cup11= new ProductBuilder("\"I Don't Need Google My Husband Knows Every\" (white, ceramic)")
//                    .description("High quality classic ceramic mug, 311.8 ml.\n" +
//                            "Versatile: for serving coffee, tea, juice or any classic beverage.\n" +
//                            "Durable and durable: dishwasher and microwave-safe for quick and hassle-free cleaning.")
//                    .category(ProductCategories.CUP)
//                    .image1("https://www.dropbox.com/scl/fi/9l932e6gc751rcvog4qup/img1.jpeg?rlkey=9ad8t6kc55etnlnsovkwp2cy0&st=bebg71uc&raw=1")
//                    .image2("https://www.dropbox.com/scl/fi/ezwppcsk51p34gw1jy1ga/img2.jpeg?rlkey=kq4lmf95iy6f45mdtovjrmitj&st=jq4i9gdk&raw=1")
//                    .image3("https://www.dropbox.com/scl/fi/8ujtn5tfspn34cq790x4x/img3.jpeg?rlkey=vjfvonyjlhjms2yulqrksoltc&st=j8y8d5ih&raw=1")
//                    .image4("https://www.dropbox.com/scl/fi/1k5mlv4wxqmys36ef6sg1/img4.jpeg?rlkey=4m4vwbfnk60cz42nzfbh7p0pw&st=45dkmlhi&raw=1")
//                    .image5(baseImage)
//                    .price(14.08).currency("CHF").remained(150).sold(1)
//                    .build();
//
//            List<Product> cups = List.of(cup1, cup2, cup3, cup4, cup5, cup6, cup7, cup8, cup9, cup10, cup11);
//
//            productRepository.saveAll(cups);
//
//            // T-Shirts products
//            // Sweatshirts products
//            // productRepository.saveAll(List.of(p1, p2, p3, p4, p5)); // Uncomment and continue with similar pattern for other product types
//        };
//    }
}
