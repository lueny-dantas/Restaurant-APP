package br.com.alura.panucci.sampledata

import androidx.compose.ui.tooling.preview.datasource.LoremIpsum
import br.com.alura.panucci.model.Product
import java.math.BigDecimal
import kotlin.random.Random

private val loremName = LoremIpsum(Random.nextInt(10)).values.first()
private val loremDesc = LoremIpsum(Random.nextInt(30)).values.first()


val sampleHighlightsProducts = listOf(
    Product(
        name = "Espaguete ao frutos do mar",
        price = BigDecimal("79.99"),
        description = "Espaguete com camarão, mexilhões, lula e polvo ao molho do chef.",
        image = "https://s2.glbimg.com/TqRJtLZOmLyV-a1O5AnSRH7w7wQ=/0x0:260x160/984x0/smart/filters:strip_icc()/g.glbimg.com/og/gs/gsat5/f/thumbs/materia/2014/04/30/spaghetti-frutos-mar-260_471957270184942383.jpg "

    ),
    Product(
        name = "Lula grelhada com arroz negro",
        price = BigDecimal("109.99"),
        description = "Lula grelhada e salteada no alho e ervas acompanhado com o delicioso arroz negro ao vinho branco e parmesão e purê de beterraba do chef.",
        image = "https://storage.googleapis.com/imagens_videos_gou_cooking_prod/production/mesas/2020/10/b7cf2b53-risoto-de-lula-e-camarao-em-arroz-negro-_-tm-camarao-polvo-lula-_-heleninha-drumond-_-alta-1.jpg "
    ),
    Product(
        name = "Filé mignon ao molho de ervas",
        price = BigDecimal("79.99"),
        description = "Filé mignon ao molho de mostardas acompanhado de batata sauté.",
        image = "https://storage.googleapis.com/imagens_videos_gou_cooking_prod/production/cooking/cropped_temp_109335374754c68fae234e35.00519794_.jpg  "

    ),
    Product(
        name = "Capeletti de búfala e alcachofra",
        price = BigDecimal("65.99"),
        description = " Massa servida ao molho 4 queijos",
        image = "https://soddisfatto.com.br/wp-content/uploads/2016/11/pasta-recheada-sorrentino.jpg "

    ),

    )
val sampleMenuProducts = listOf(
    Product(
        name = "Filé de peixe fresco a belle Meuniere",
        price = BigDecimal("59.99"),
        description = "Filé de peixe fresco do dia, grelhado ao molho de champignon, camarão e alcaparras. É acompanhado de verduras na manteiga e arroz.",
        image = "https://www.vivercascavel.com.br/fotos/g_20150821_140643_34.jpg"
    ),
    Product(
        name = "Espaguete ao frutos do mar",
        price = BigDecimal("79.99"),
        description = "Espaguete com camarão, mexilhões, lula e polvo ao molho do chef.",
        image = "https://s2.glbimg.com/TqRJtLZOmLyV-a1O5AnSRH7w7wQ=/0x0:260x160/984x0/smart/filters:strip_icc()/g.glbimg.com/og/gs/gsat5/f/thumbs/materia/2014/04/30/spaghetti-frutos-mar-260_471957270184942383.jpg "

    ),
    Product(
        name = "Badejo à brasileira",
        price = BigDecimal("70.99"),
        description = "Filé de badejo com crosta de castanha e camarão acompanhado de arroz branco.",
        image = "https://padariabrasileira.com.br/wp-content/uploads/2021/11/1-ALC-e-CONG-Badejo-em-crosta-de-castanha-Aprovada-1-e1637601353663.jpg"
    ),
    Product(
        name = "Filé mignon ao molho de ervas",
        price = BigDecimal("79.99"),
        description = "Filé mignon ao molho de mostardas acompanhado de batata sauté.",
        image = "https://storage.googleapis.com/imagens_videos_gou_cooking_prod/production/cooking/cropped_temp_109335374754c68fae234e35.00519794_.jpg  "

    ),
    Product(
        name = "Lula grelhada com arroz negro",
        price = BigDecimal("109.99"),
        description = "Lula grelhada e salteada no alho e ervas acompanhado com o delicioso arroz negro ao vinho branco e parmesão e purê de beterraba do chef.",
        image = "https://storage.googleapis.com/imagens_videos_gou_cooking_prod/production/mesas/2020/10/b7cf2b53-risoto-de-lula-e-camarao-em-arroz-negro-_-tm-camarao-polvo-lula-_-heleninha-drumond-_-alta-1.jpg "
    ),
    Product(
        name = " Nhoque de mandioquinha",
        price = BigDecimal("49.99"),
        description = "Massa Servido ao molho fresco. ",
        image = "https://claudia.abril.com.br/wp-content/uploads/2020/02/receita-nhoque-mandioquinha-sem-gluten1.jpg"
    ),
    Product(
        name = "Capeletti de búfala e alcachofra",
        price = BigDecimal("65.99"),
        description = " Massa servida ao molho 4 queijos",
        image = "https://soddisfatto.com.br/wp-content/uploads/2016/11/pasta-recheada-sorrentino.jpg "

    )
)

val sampleDrinks = listOf(
    Product(
        name = "Água Mineral",
        price = BigDecimal("3.99"),
        description = " Garraja 300ml ",
        image = "https://www.emporionestle.com.br/media/catalog/product/7/8/7896062801203_1.jpg?quality=80&bg-color=255,255,255&fit=bounds&height=265&width=265&canvas=265:265 "

    ),
    Product(
        name = "Suco de Laranja",
        price = BigDecimal("12.99"),
        description = " Jarra 1 lt",
        image = "https://w7.pngwing.com/pngs/363/444/png-transparent-pitcher-glass-fruit-orange-juice-citrus-dessert.png"

    ),
    Product(
        name = "Cappuccino",
        price = BigDecimal("14.99"),
        description = "Xícara com 150ml ",
        image = "https://img.freepik.com/vetores-gratis/xicara-branca-de-cafe-quente-com-canela-no-pires-e-feijao-na-mesa-de-madeira-realista_1284-56783.jpg?w=2000 "

    ),
    Product(
        name = "Cidra de maçã negroni",
        price = BigDecimal("24.99"),
        description = "Taça com 200 ml",
        image = "https://receitanatureba.com/wp-content/uploads/2017/12/Como-Fazer-Sidra-de-Maca-Com-ou-Sem-Alcool.jpg "

    ),
    Product(
        name = "Refrigerante",
        price = BigDecimal("5.99"),
        description = " Lata 350ml",
        image = "https://hotdogbrasil.com.br/wp-content/uploads/2019/08/refris.png"

    ),
    Product(
        name = "Vinho Tinto",
        price = BigDecimal("169.99"),
        description = " Garrrafa 1 lt",
        image = "https://roccadivino.com/wp-content/uploads/2021/11/27.png "

    ),
    Product(
        name = "Tequila Sunrise",
        price = BigDecimal("17.99"),
        description = " Drink com 300 ml",
        image ="https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRXe0Ru6vjXiPZmk3UazlWSA1KityLtd2rabw&usqp=CAU"
    ),
    Product(
        name = "Ponche de Run",
        price = BigDecimal("19.99"),
        description = " Drink com 300 ml",
        image = "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcT9UAIdtA5Gck9GnJsyiARulwEcTBhu7JNMtQ&usqp=CAU"
    ),

)


val sampleProducts: List<Product> = listOf(
    *sampleHighlightsProducts.toTypedArray(), *sampleMenuProducts.toTypedArray(), *sampleDrinks.toTypedArray()

)

val sampleCheckout = listOf(
    Product(
        name = "Espaguete ao frutos do mar",
        price = BigDecimal("79.99"),
        description = "Espaguete com camarão, mexilhões, lula e polvo ao molho do chef.",
        image = "https://s2.glbimg.com/TqRJtLZOmLyV-a1O5AnSRH7w7wQ=/0x0:260x160/984x0/smart/filters:strip_icc()/g.glbimg.com/og/gs/gsat5/f/thumbs/materia/2014/04/30/spaghetti-frutos-mar-260_471957270184942383.jpg "

    ),
    Product(
        name = " Nhoque de mandioquinha",
        price = BigDecimal("49.99"),
        description = "Massa Servido ao molho fresco. ",
        image = "https://claudia.abril.com.br/wp-content/uploads/2020/02/receita-nhoque-mandioquinha-sem-gluten1.jpg"
    ),
    Product(
        name = "Suco de Laranja",
        price = BigDecimal("12.99"),
        description = " Jarra 1 lt",
        image = "https://w7.pngwing.com/pngs/363/444/png-transparent-pitcher-glass-fruit-orange-juice-citrus-dessert.png"

    ),
    Product(
        name = "Cappuccino",
        price = BigDecimal("14.99"),
        description = "Xícara com 150ml ",
        image = "https://img.freepik.com/vetores-gratis/xicara-branca-de-cafe-quente-com-canela-no-pires-e-feijao-na-mesa-de-madeira-realista_1284-56783.jpg?w=2000 "

    ),

)



