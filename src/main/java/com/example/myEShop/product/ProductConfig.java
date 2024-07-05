package com.example.myEShop.product;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import java.util.List;

/**
 * Configuration class for setting up product-related beans.
 * <p>
 * This configuration enables JPA auditing and can be used to initialize
 * the product repository with sample data.
 * </p>
 */
@Configuration
@EnableJpaAuditing
public class ProductConfig {
    /**
     * Base URL for the default image used for products.
     */
    String baseImage = "https://www.dropbox.com/scl/fi/ozh335x54aov3b2vlpej9/base_image.png?rlkey=rury7lnqs27ouz0xww3iw6aud&st=ldrqd9fm&raw=1";

    /**
     * CommandLineRunner bean to initialize the product repository with sample data.
     * <p>
     * This method sets up initial data for products such as cups, T-shirts, and sweatshirts.
     * </p>
     *
     * @param productRepository the product repository to initialize
     * @return a CommandLineRunner to execute the initialization
     */
    @Bean
    CommandLineRunner commandLineRunnerProduct(ProductRepository productRepository) {
        return args -> {
            Product cup1 = new ProductBuilder("12oz White Ceramic Mug Go Away I'm Coding Coder Programmer")
                    .description("Meaning: For a programmer who needs to focus on coding all day, this funny mug with the quote \"Go Away, I'm Coding\" will be their next favorite companion at work or in the office. They can place it next to their work table and people will get the message they can't say out loud. Sarcastic but fun mug to take a break from programming and remind them that there's more to life than codes and algorithms." +
                            "\nDishwasher and Microwave Safe: These great mugs can be washed straight in the dishwasher, all day and every day, which means they can save you time and be more hygienic. Perfect for your hot or cold drinks Easily reheat the coffee or tea you forgot to drink right away because it is microwave-safe, very convenient and perfect for your busy lifestyle.")
                    .category(ProductCategories.CUP)
                    .image1("https://www.dropbox.com/scl/fi/qyeecn5f7nb3tgkor32wx/img1.jpeg?rlkey=rvb3xcx1m9znz8m07r2ljfbu0&st=sy3s7jz9&raw=1")
                    .image2("https://www.dropbox.com/scl/fi/0ukduhcvh0rm4xo4d4rj6/img2.jpeg?rlkey=wlrjedmm4kff38p3tmed5nf0m&st=er31ex88&raw=1")
                    .image3("https://www.dropbox.com/scl/fi/6w13kb55sk8zro7j09tpm/img3.jpeg?rlkey=jn6hcnrgbzt2xtar569u0a1ic&st=fkf6likk&raw=1")
                    .image4("https://www.dropbox.com/scl/fi/5971zm49yj9ka4epco2fa/img4.jpeg?rlkey=qb0psthcy4njbiosbqob0ipj5&st=vu53awne&raw=1")
                    .price(14.08)
                    .currency("CHF")
                    .remained(150)
                    .sold(10)
                    .build();

            Product cup2 = new ProductBuilder("99WakeUp Loading Future Software Engineer Coffee")
                    .description("Microwave safe")
                    .category(ProductCategories.CUP)
                    .image1("https://www.dropbox.com/scl/fi/dv4k8sk3xrp1bwweb4y8b/img1.jpeg?rlkey=v97jeaxe47eg0d74crh94rqui&st=pa7ph1m0&raw=1")
                    .image2(baseImage).image3(baseImage).image4(baseImage)
                    .price(14.08)
                    .currency("CHF")
                    .remained(150)
                    .sold(18)
                    .build();

            Product cup3 = new ProductBuilder("Punkcards - Funny Coffee Mugs - \"Engineer Someone Who Does\"")
                    .description("Printed on both sides This fun conversation starter makes a perfect gift. High Quality Ceramic Mug - Cheers to Caffeinated Laughs - Funny Coffee Mug! This is a perfect gift for any tea or coffee lover" +
                            "\nStart your day with a smile - Get our funny coffee mug! Packaged in a break-proof mug box to ensure safe delivery to give as a fun and unique birthday gift.")
                    .category(ProductCategories.CUP)
                    .image1("https://www.dropbox.com/scl/fi/bo8wl63q88ettp09vpbq6/img1.jpeg?rlkey=qzv9szvt7duo9qety9ig77opf&st=gla5qksh&raw=1")
                    .image2("https://www.dropbox.com/scl/fi/2dc4g26p9lcdnkv6eeywu/img2.jpeg?rlkey=su0zslmfj3f4iamkevz67lnhc&st=qdxifjic&raw=1")
                    .image3("https://www.dropbox.com/scl/fi/6al41chcwcb9x5k85b2in/img3.jpeg?rlkey=mzr44nl7lzz6lg7qz3gy59yvd&st=1pngalqa&raw=1")
                    .image4("https://www.dropbox.com/scl/fi/vhr70xnkqewnmu3uiv1ua/img4.jpeg?rlkey=i96s5tns4fnkd48277je4lkq7&st=lwu2wq64&raw=1")
                    .price(21.85)
                    .currency("CHF")
                    .remained(150)
                    .sold(1)
                    .build();

            Product cup4 = new ProductBuilder("IT Code Programmer Coffee Mug")
                    .description("Gift idea for IT enthusiasts, specialized technicians and software developers: all the nerds behind the PC who deal with code, IT and software. Ideal for Home Office developers." +
                            "\nHigh Quality: Dishwasher and microwave safe. Capacity: about 330ml. 100% high quality ceramic. It can be used as coffee mug, cocoa cup and coffee mug.")
                    .category(ProductCategories.CUP)
                    .image1("https://www.dropbox.com/scl/fi/tmvdhimun5dch9tj2wp6e/img1.jpeg?rlkey=3dfpf02g3oitefo3gnqulifnp&st=p45x5ze2&raw=1")
                    .image2("https://www.dropbox.com/scl/fi/k2t2j4jyv6syttsg3uuiq/img2.jpeg?rlkey=4k9gc8ae9vf7rsx1697phbazv&st=1n8rm54x&raw=1")
                    .image3("https://www.dropbox.com/scl/fi/2i09ttm9neanr19qg89aj/img3.jpeg?rlkey=cd119keuwojwvnkks29h1tw8m&st=n6zuzzf7&raw=1")
                    .image4("https://www.dropbox.com/scl/fi/z6h0gpjsh72ejw2ys4l7u/img4.jpeg?rlkey=qom91jvndplshjdrs4cc4o2s4&st=iq2whfwz&raw=1")
                    .price(17.90)
                    .currency("CHF")
                    .remained(150)
                    .sold(29)
                    .build();

            Product cup5 = new ProductBuilder("Trust Me I'm an Engineer Classic White Ceramic Mug 325ml")
                    .description("\nFunny Engineer Coffee Mug - Dishwasher and Microwave Safe - Printed on Both Sides. It can be used for home and office.")
                    .category(ProductCategories.CUP)
                    .image1("https://www.dropbox.com/scl/fi/2tifwuoqirmhx6ako5sjz/img1.jpeg?rlkey=4fdyvoeg9g9kj061r4x73xq7j&st=qu5iod8i&raw=1")
                    .image2("https://www.dropbox.com/scl/fi/v0r4xsm7r1wpvo3h3mkxm/img2.jpeg?rlkey=970ixspibcov7id3jzip1x5v0&st=lmlgy0ix&raw=1")
                    .image3(baseImage).image4(baseImage)
                    .price(16.95)
                    .currency("CHF")
                    .remained(150)
                    .sold(3)
                    .build();

            Product cup6 = new ProductBuilder("MG3467 Go Away I'm Coding printed ceramic tea and coffee mug")
                    .description("100% Dishwasher and Microwave safe. " +
                            "\nHigh quality prints using the dye sublimation method.")
                    .category(ProductCategories.CUP)
                    .image1("https://www.dropbox.com/scl/fi/ex11jl4jjr7q8z5xns7oc/img1.jpeg?rlkey=197edbx4qabfvgea86ugfcs17&st=oq9zhbjq&raw=1")
                    .image2(baseImage).image3(baseImage).image4(baseImage)
                    .price(12.85)
                    .currency("CHF")
                    .remained(150)
                    .sold(5)
                    .build();

            Product cup7 = new ProductBuilder("Looking For Commitment - White Ceramic Mug")
                    .description("Sturdy ceramic construction\n" +
                            "Suitable for hot and cold drinks")
                    .category(ProductCategories.CUP)
                    .image1("https://www.dropbox.com/scl/fi/sis3vhjz68xn8hs29ch6b/img1.jpeg?rlkey=3btx397vgi99916cspubtiqnp&st=jufsi57e&raw=1")
                    .image2(baseImage).image3(baseImage).image4(baseImage)
                    .price(17.79)
                    .currency("CHF")
                    .remained(150)
                    .sold(30)
                    .build();

            Product cup8 = new ProductBuilder("Github Contributions - White Ceramic Mug")
                    .description("Sturdy ceramic construction\n" +
                            "Suitable for hot and cold drinks")
                    .category(ProductCategories.CUP)
                    .image1("https://www.dropbox.com/scl/fi/ptki608x7gcn6fiihve3r/img1.jpeg?rlkey=ssp0ps7scdwa6deonqndxmqyl&st=o3vmdu5h&raw=1")
                    .image2(baseImage).image3(baseImage).image4(baseImage)
                    .price(17.79)
                    .currency("CHF")
                    .remained(150)
                    .sold(32)
                    .build();

            Product cup9 = new ProductBuilder("Limited \"Facebook like in the shape of a mug")
                    .description("It seems like everyone is posting who online they are up to these days and now even your cup of tea is always up to it.\n" +
                            "A unique mug that will definitely stand out and a great gift for techie people.")
                    .category(ProductCategories.CUP)
                    .image1("https://www.dropbox.com/scl/fi/canv0ogwcf41kyejv139w/img1.jpeg?rlkey=2omc8mkafvlhs42gefcku1wv3&st=ua2vkhvz&raw=1")
                    .image2(baseImage).image3(baseImage).image4(baseImage)
                    .price(18.16)
                    .currency("CHF")
                    .remained(150)
                    .sold(7)
                    .build();

            Product cup10= new ProductBuilder("I Don't Need Google My Wife Knows Everything - High Quality Mug")
                    .description("High quality ceramic coffee mug\n" +
                            "Design is on both sides of the mug\n" +
                            "Dishwasher and microwave safe\n" +
                            "Capacity of 312ml")
                    .category(ProductCategories.CUP)
                    .image1("https://www.dropbox.com/scl/fi/nfk7mucjd4n6o2sw3zhsx/img1.jpeg?rlkey=3mclzh57845dit71t8dzw3qor&st=78ywu0hb&raw=1")
                    .image2(baseImage).image3(baseImage).image4(baseImage)
                    .price(11.19)
                    .currency("CHF")
                    .remained(150)
                    .sold(50)
                    .build();

            Product cup11= new ProductBuilder("\"I Don't Need Google My Husband Knows Every\" (white, ceramic)")
                    .description("High quality classic ceramic mug, 311.8 ml.\n" +
                            "Versatile: for serving coffee, tea, juice or any classic beverage.\n" +
                            "Durable and durable: dishwasher and microwave-safe for quick and hassle-free cleaning.")
                    .category(ProductCategories.CUP)
                    .image1("https://www.dropbox.com/scl/fi/9l932e6gc751rcvog4qup/img1.jpeg?rlkey=9ad8t6kc55etnlnsovkwp2cy0&st=bebg71uc&raw=1")
                    .image2("https://www.dropbox.com/scl/fi/ezwppcsk51p34gw1jy1ga/img2.jpeg?rlkey=kq4lmf95iy6f45mdtovjrmitj&st=jq4i9gdk&raw=1")
                    .image3("https://www.dropbox.com/scl/fi/8ujtn5tfspn34cq790x4x/img3.jpeg?rlkey=vjfvonyjlhjms2yulqrksoltc&st=j8y8d5ih&raw=1")
                    .image4("https://www.dropbox.com/scl/fi/1k5mlv4wxqmys36ef6sg1/img4.jpeg?rlkey=4m4vwbfnk60cz42nzfbh7p0pw&st=45dkmlhi&raw=1")
                    .price(14.08).currency("CHF").remained(150).sold(1)
                    .build();

            List<Product> cups = List.of(cup1, cup2, cup3, cup4, cup5, cup6, cup7, cup8, cup9, cup10, cup11);

            productRepository.saveAll(cups);

            // T-Shirts products

            Product tshirt1 = new ProductBuilder("Let Me Ask Funny GPT Chat ChatGPT T-Shirt")
                    .description("Solid colour: 100% Cotton; Ash Grey: 90% Cotton, 10% Polyester; Other Tones: 50% Cotton, 50% Polyester \n"+
                            "Machine washable")
                    .category(ProductCategories.TSHIRT)
                    .image1("https://www.dropbox.com/scl/fi/kyczds3qd9267uabffxh5/img1.jpeg?rlkey=h2l8chmc45nywbm0tap41t07v&st=fcqgqyfm&raw=1")
                    .image2(baseImage).image3(baseImage).image4(baseImage)
                    .price(16.99).currency("CHF").remained(120).sold(20)
                    .build();

            Product tshirt2 = new ProductBuilder("GUNMANTOR Github Contributions Black T-Shirt for Men with Short Sleeves Crew Neck T-Shirt Gray Black")
                    .description("Comfortable to wear everyday and any occasion\n" +
                            "High quality European print\n" +
                            "Soft cotton that feels comfortable on your skin")
                    .category(ProductCategories.TSHIRT)
                    .image1("https://www.dropbox.com/scl/fi/f4xppa5vgpjqgalafa4px/img1.jpg?rlkey=v8s8sagvor5f3n3fpb2e6a7z4&st=qm6qjtl7&raw=1")
                    .image2(baseImage).image3(baseImage).image4(baseImage)
                    .price(17.79).currency("CHF").remained(120).sold(10)
                    .build();

            Product tshirt3 = new ProductBuilder("GUNMANTOR Github Contributions White T-Shirt for Men with Short Sleeves Crew Neck T-Shirt White Mens")
                    .description("Comfortable to wear everyday and any occasion\n" +
                            "High quality European print\n" +
                            "Soft cotton that feels comfortable on your skin")
                    .category(ProductCategories.TSHIRT)
                    .image1("https://www.dropbox.com/scl/fi/iaaisq1bmf8fjwc1u80y1/img1.jpg?rlkey=ysj9b1y83qre4iq5t3gjkeewp&st=pjkp2ow2&raw=1")
                    .image2(baseImage).image3(baseImage).image4(baseImage)
                    .price(16.79).currency("CHF").remained(120).sold(13)
                    .build();

            Product tshirt4 = new ProductBuilder("MENGJI Github O Neck Men T-Shirt T-Shirt Black")
                    .description("The perfect gift or gift for any occasion; Christmas, Birthday, Father's or Mother's Day, Valentine's Day, or just a treat; your mom, dad, brother, sister, uncle, aunt or best friend.\n" +
                            "Made of high-quality cotton material, soft and smooth, comfortable and dry, happy to accompany you through spring, summer, autumn and winter\n" +
                            "We use the latest printing methods to ensure printed designs are as durable as t-shirts.\n" +
                            "Hand washable or machine washable, do not soak for a long time, do not bleach, the temperature of the washing liquid should not exceed 30ºC.")
                    .category(ProductCategories.TSHIRT)
                    .image1("https://www.dropbox.com/scl/fi/834gevwhpht349wjl546m/img1.jpg?rlkey=mtlz35wsh9u1fdql6sfpor9hc&st=8pkyhhyr&raw=1")
                    .image2(baseImage).image3(baseImage).image4(baseImage)
                    .price(13.76).currency("CHF").remained(120).sold(50)
                    .build();

            Product tshirt5 = new ProductBuilder("FEIDT Our Github T Shirt Progmer Prog Progming EK Shirt Short Sleeve T-Shirt LH-149")
                    .description("Breathable Material – The graphic t shirt is crafted from soft, stretchy and breathable material. It won't let perspiration build up during exercise.\n" +
                            "Hand wash and machine wash, 30°C (86°F)\n" +
                            "Soft and breathable 100% cotton fabric\n" +
                            "Casual men's T-shirt with elegant front design")
                    .category(ProductCategories.TSHIRT)
                    .image1("https://www.dropbox.com/scl/fi/hyfyse4bstjmqexdh4ted/img1.jpg?rlkey=8wqd7qwj8s6hc1n9hvj6mz955&st=qu9vf80n&raw=1")
                    .image2(baseImage).image3(baseImage).image4(baseImage)
                    .price(16.19).currency("CHF").remained(120).sold(70)
                    .build();

            Product tshirt6 = new ProductBuilder("Powe Funny Design Github T Shirt Cotton O-Neck Short Sleeve T-Shirt Mans Tshirt")
                    .description("excellent workmanship\n" +
                            "Machine wash cold\n" +
                            "fits comfortably\n" +
                            "Highlight your style\n" +
                            "Round neck")
                    .category(ProductCategories.TSHIRT)
                    .image1("https://www.dropbox.com/scl/fi/e8do193psu0vthtji8h6b/img1.jpg?rlkey=mpcmgdvfg1daoojzm5fy7m9jo&st=0t32oqbl&raw=1")
                    .image2("https://www.dropbox.com/scl/fi/b42rvnagfxi0q4l05lh40/img2.jpg?rlkey=524paykq839hh8x72t5p83dzk&st=ic8v6q80&raw=1")
                    .image3("https://www.dropbox.com/scl/fi/c2lpe3a6ywm57wuo0nzzm/img3.jpg?rlkey=twbrldxxwh5y39v2rkbv3bu9t&st=golxx6ws&raw=1")
                    .image4("https://www.dropbox.com/scl/fi/har90pu49ejoq2ht7parm/img4.jpg?rlkey=nxpbkkr8p8fkvpymypnzqv8dc&st=qa0lrqh4&raw=1")
                    .price(18.57).currency("CHF").remained(120).sold(20)
                    .build();

            Product tshirt7 = new ProductBuilder("I Don't Need Google My Husband Knows Everything Funny Wife T-Shirt")
                    .description("Material Composition: Solid Color: 100% Cotton; Ash Gray: 90% Cotton, 10% Polyester; Other Tones: 50% Cotton, 50% Polyester\n" +
                            "Washing instructions: Machine wash\n" +
                            "Collar Stille: Crew Neck\n" +
                            "Sleeve Type: Short Sleeve")
                    .category(ProductCategories.TSHIRT)
                    .image1("https://www.dropbox.com/scl/fi/vp4ovd3ui6j40q9vwfubd/img1.png.jpeg?rlkey=nyza7r7icwk9pnkioytzwwhs5&st=hwbltrc5&raw=1")
                    .image2(baseImage).image3(baseImage).image4(baseImage)
                    .price(16.99).currency("CHF").remained(120).sold(10)
                    .build();

            Product tshirt8 = new ProductBuilder("I don't need Google, my wife knows everything | Funny T-Shirt")
                    .description("Material Composition: Solid Color: 100% Cotton; Ash Gray: 90% Cotton, 10% Polyester; Other Tones: 50% Cotton, 50% Polyester\n" +
                            "Washing instructions: Machine wash\n" +
                            "Collar Stille: Crew Neck\n" +
                            "Sleeve Type: Short Sleeve")
                    .category(ProductCategories.TSHIRT)
                    .image1("https://www.dropbox.com/scl/fi/ccrjmcx9j49zu1xw272ih/img1.png.jpeg?rlkey=b8nme4rm6hp697gqq9gf302pt&st=wx7hlv8e&raw=1")
                    .image2(baseImage).image3(baseImage).image4(baseImage)
                    .price(16.99).currency("CHF").remained(120).sold(20)
                    .build();

            List<Product> tshirts = List.of(tshirt1, tshirt2, tshirt3, tshirt4, tshirt5, tshirt6, tshirt7, tshirt8);

            productRepository.saveAll(tshirts);


            // Sweatshirts products

            Product sweatshirt1 = new ProductBuilder("Computer Evolution Retro Developer Programmer Hoodie")
                    .description("Material Composition: Solid Color: 80% Cotton, 20% Polyester; Grey Melange: 75-78% Cotton, 22-25% Polyester; Dark Grey: 50% Cotton, 50% Polyester\n" +
                            "Washing instructions: Machine wash in cold water with same color garments, tumble dry at low temperature. Long sleeves\n" +
                            "Collar stille; Hooded neckline\n" +
                            "Cut Type: Classic")
                    .category(ProductCategories.SWEATSHIRT)
                    .image1("https://www.dropbox.com/scl/fi/flionezcdppn7jkkrq5gx/img1.png.jpeg?rlkey=m9pthr0rltnzkxi5ok52uonew&st=idlfq443&raw=1")
                    .image2(baseImage).image3(baseImage).image4(baseImage)
                    .price(33.99).currency("CHF").remained(55).sold(47)
                    .build();

            Product sweatshirt2 = new ProductBuilder("Sudo Install Coffee Informatic Admin Admin Hoodie")
                    .description("Material composition: Solid color: 80% Cotton, 20% Polyester; Grey Melange: 75-78% Cotton, 22-25% Polyester; Dark grey: 50% Cotton, 50% Polyester\n" +
                            "Washing instructions: Machine wash in cold water with same color garments, tumble dry at low temperature. Long sleeves\n" +
                            "Collar Stille: Hooded neckline\n" +
                            "Cut type: Classic")
                    .category(ProductCategories.SWEATSHIRT)
                    .image1("https://www.dropbox.com/scl/fi/mwbq4yd4in00o7dbux2kn/img1.png.jpeg?rlkey=60rl16pvj8y1on47soxqk9jm2&st=5df4xgmj&raw=1")
                    .image2(baseImage).image3(baseImage).image4(baseImage)
                    .price(33.99).currency("CHF").remained(55).sold(5)
                    .build();

            Product sweatshirt3 = new ProductBuilder("Programmer Developer Computer Geek Coder Nerd C ++ CSS Hoodie")
                    .description("Material Composition: Solid Color: 80% Cotton, 20% Polyester; Grey Melange: 75-78% Cotton, 22-25% Polyester; Dark Grey: 50% Cotton, 50% Polyester\n" +
                            "Washing instructions: Machine wash\n" +
                            "Weave Type: Twill\n" +
                            "Collar Stille: Hooded neckline")
                    .category(ProductCategories.SWEATSHIRT)
                    .image1("https://www.dropbox.com/scl/fi/tjebt0hwa79xkswz2pyj2/img1.png.jpeg?rlkey=iw3glbjo2zq2ig8cj3n1jmwph&st=j611xi3p&raw=1")
                    .image2(baseImage).image3(baseImage).image4(baseImage)
                    .price(32.99).currency("CHF").remained(55).sold(47)
                    .build();

            Product sweatshirt4 = new ProductBuilder("Computer Programmer Evolution Computer Nerd Hoodie")
                    .description("Material Composition: Solid Color: 80% Cotton, 20% Polyester; Grey Melange: 75-78% Cotton, 22-25% Polyester; Dark Grey: 50% Cotton, 50% Polyester\n" +
                            "Washing instructions: Machine wash in cold water with same color garments, tumble dry at low temperature. Long sleeves\n" +
                            "Collar Stille: Hooded neckline\n" +
                            "Sleeve Type: Long Sleeve")
                    .category(ProductCategories.SWEATSHIRT)
                    .image1("https://www.dropbox.com/scl/fi/tnsi4bfhxz0wfvql0ydle/img1.png.jpeg?rlkey=yrf6nvrxb8u4578e3ywe29l6c&st=tnw328o2&raw=1")
                    .image2(baseImage).image3(baseImage).image4(baseImage)
                    .price(31.99).currency("CHF").remained(60).sold(9)
                    .build();

            Product sweatshirt5 = new ProductBuilder("Costume Error 404 Funny Computer Programmer Hoodie")
                    .description("Material Composition: Solid Color: 80% Cotton, 20% Polyester; Grey Melange: 75-78% Cotton, 22-25% Polyester; Dark Grey: 50% Cotton, 50% Polyester\n" +
                            "Washing instructions: Machine wash in cold water with same color garments, tumble dry at low temperature. Long sleeves\n" +
                            "Collar Stille: Hooded neckline\n" +
                            "Sleeve Type: Long Sleeve")
                    .category(ProductCategories.SWEATSHIRT)
                    .image1("https://www.dropbox.com/scl/fi/991u877jzr5j3ecnnyos3/img1.png.jpeg?rlkey=421qep9qdnhcjavxndtklv0ez&st=y3fjycuw&raw=1")
                    .image2(baseImage).image3(baseImage).image4(baseImage)
                    .price(32.99).currency("CHF").remained(70).sold(20)
                    .build();

            Product sweatshirt6 = new ProductBuilder("GUNMANTOR Github Contributions Pullover Black Long Sleeve Unisex Women's Hoodie Mens Womens Black")
                    .description("Comfortable to wear everyday and any occasion\n" +
                            "High quality European print\n" +
                            "Soft cotton that feels comfortable on your skin")
                    .category(ProductCategories.SWEATSHIRT)
                    .image1("https://www.dropbox.com/scl/fi/qffwg7xilu4zxt256si14/img1.jpg?rlkey=4k1rml0bqwgtrr6kiyzzh62c6&st=28i19rmv&raw=1")
                    .image2(baseImage).image3(baseImage).image4(baseImage)
                    .price(37.79).currency("CHF").remained(96).sold(4)
                    .build();

            Product sweatshirt7 = new ProductBuilder("GUNMANTOR Github Contributions Pullover Grey Long Sleeve Unisex Hoodie Mens Womens Grey")
                    .description("Comfortable to wear everyday and any occasion\n" +
                            "High quality European print\n" +
                            "Soft cotton that feels comfortable on your skin")
                    .category(ProductCategories.SWEATSHIRT)
                    .image1("https://www.dropbox.com/scl/fi/5pn8y6mrmdq6s8w6xma26/img1.jpg?rlkey=34zonnfgqmeq2dwesgsy6qivi&st=q5vxoml7&raw=1")
                    .image2(baseImage).image3(baseImage).image4(baseImage)
                    .price(35.79).currency("CHF").remained(97).sold(3)
                    .build();

            Product sweatshirt8 = new ProductBuilder("Ameretee Hacker Port Numbers Simple Sweater Black Crew Neck Unisex")
                    .description("Made primarily of cotton (70%)\n" +
                            "Age of innovation: excellence in printing\n" +
                            "Supreme quality\n" +
                            "Durable and environmentally friendly ink\n" +
                            "Standard fit")
                    .category(ProductCategories.SWEATSHIRT)
                    .image1("https://www.dropbox.com/scl/fi/uytj3qpy3f8gq6gv8c3pq/img1.jpg?rlkey=htatklg1zfvksmk9p1kn3auel&st=l7icw737&raw=1")
                    .image2(baseImage).image3(baseImage).image4(baseImage)
                    .price(35.49).currency("CHF").remained(60).sold(30)
                    .build();

            Product sweatshirt9 = new ProductBuilder("Pullover Warm Hoodies Funny Design Github Hoody Long Sleeve Hoodie Mans")
                    .description("This hoodie features front pockets and a drawstring. Made of 60% cotton, 40% polyester, comfortable, soft, breathable and quick-drying.\n" +
                            "Comfortable pullover crew neck, easy to put on and take off, keeping your neck comfortable.\n" +
                            "It can be machine washed or hand washed, the water temperature should not exceed 30℃. Won't fade or shrink, making the pattern unique and beautiful.\n" +
                            "Our hoodies are suitable for daily wear, school, play, party, vacation, beach, street, are also a good choice.\n" +
                            "This item's size range is S-3XL. Please refer to our size chart to select your size. There may be 1-2cm error due to manual measurement, please understand.")
                    .category(ProductCategories.SWEATSHIRT)
                    .image1("https://www.dropbox.com/scl/fi/1ksp0qwjxpk6hg2ku08vg/img1.jpg?rlkey=mp5m0yzid3h9ztettajn9v9bs&st=wzqx37el&raw=1")
                    .image2("https://www.dropbox.com/scl/fi/qddbsbevl03ksest09ggm/img2.jpg?rlkey=1z786gjslipx3bs4y4qtae6qx&st=zjrr5y2q&raw=1")
                    .image3("https://www.dropbox.com/scl/fi/cxfvwjcj4i6jhluhos68p/img3.jpg?rlkey=qn1ub4cpz4ju0prlbsfgx5fus&st=9q8l5s47&raw=1")
                    .image4("https://www.dropbox.com/scl/fi/9sb58ohjcqrl6p5bkfgc6/img4.jpg?rlkey=7u5xe10bmnqtyehldycw3lmxd&st=w806sum5&raw=1")
                    .price(27.98).currency("CHF").remained(55).sold(47)
                    .build();

            List<Product> sweatshirts = List.of(sweatshirt1, sweatshirt2, sweatshirt3, sweatshirt4, sweatshirt5, sweatshirt6, sweatshirt7, sweatshirt8, sweatshirt9);

            productRepository.saveAll(sweatshirts);
        };
    }
}
