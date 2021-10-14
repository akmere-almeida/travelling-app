package com.akmere.travelling_app.data

import com.akmere.travelling_app.data.model.AddressData
import com.akmere.travelling_app.data.model.GalleryData
import com.akmere.travelling_app.data.model.ImageData
import com.akmere.travelling_app.data.model.PackageOfferData

object DataFixtures {

    val rioPackageOffer = PackageOfferData(
        name = "Pacote Rio de Janeiro",
        isAvailable = true,
        description = "<ul>\r\n\t<li><strong>A&eacute;reo:</strong>&nbsp;Passagem" +
            " a&eacute;rea de ida e volta entre a cidade de origem e&nbsp;<strong>Rio" +
            " de Janeiro</strong>&nbsp;- Aeroporto Internacional Tom Jobim -&nbsp;Gale&atilde" +
            ";o (GIG)&nbsp;ou&nbsp;o Aeroporto Santos-Dumont (SDU), em classe" +
            " econ&ocirc;mica,&nbsp;<strong>podendo haver conex&atilde;o e/ou escala</strong>" +
            ".</li>\r\n\t<li><strong>Bagagem:</strong>&nbsp;Este pacote permite levar uma" +
            " bagagem de m&atilde;o com at&eacute; 10 quilos. Desta forma, voc&ecirc;" +
            " poder&aacute; levar uma mochila ou bolsa (que dever&aacute; ser acomodada" +
            " debaixo do seu assento) e uma bagagem de m&atilde;o (que dever&aacute; caber" +
            " no compartimento superior do avi&atilde;o).</li>\r\n\t<li>\r\n\t<p><strong>" +
            "Hospedagem:&nbsp;</strong>Em<strong>&nbsp;Rio de Janeiro&nbsp;</strong>no&nbsp" +
            ";Hotel Atl&acirc;ntico Tower, Hotel Atl&acirc;ntico Prime, Hotel " +
            "Bandeirantes&nbsp;ou outro&nbsp;de categoria&nbsp;econ&ocirc;mica, com " +
            "caf&eacute; da manh&atilde;.</p>\r\n\r\n\t<p>A hospedagem ser&aacute; " +
            "definida pelo Hurb de acordo com a disponibilidade e tarif&aacute;rio " +
            "promocional.&nbsp;</p>\r\n\t</li>\r\n\t<li>\r\n\t<p><strong>Obs.:</strong>" +
            " o pacote <strong>n&atilde;o</strong> inclui transfer.</p>\r\n\t" +
            "</li>\r\n\t<li>\r\n\t<p><strong>Aten&ccedil;&atilde;o:</strong>&nbsp;" +
            "As di&aacute;rias s&atilde;o contabilizadas pelas noites dormidas a " +
            "partir da sua chegada no hotel.</p>\r\n\r\n\t<p><strong>Idiomas do hotel" +
            ":</strong>&nbsp;Portugu&ecirc;s, ingl&ecirc;s ou espanhol</p>\r\n\t" +
            "</li>\r\n</ul>\r\n",

        addressData = AddressData(city = "Rio de Janeiro", country = "Brasil"),
        galleryData = GalleryData(
            images = listOf(
                ImageData(
                    url = "https://thumbcdn-z.hotelurbano.net/hobxsQEe3e_2Otv_ed18T_E9afs=/" +
                        "origxorig/center/middle/filters:quality(70)/https://" +
                        "s3.amazonaws.com/legado-prod/prod/ofertas/imagens/2021/03/01/12/" +
                        "10/pacote_rio_de_janeiro_cristo_redentor_1044x696__1_.png",

                    description = "Imagem do Cristo Redentor visto a partir do Corcovado"
                )
            )
        )
    )

    val paratyPackageOffer = PackageOfferData(
        name = "Pacote Paraty",
        isAvailable = true,
        description = "<p>O pacote&nbsp;<strong>inclui</strong>:</p>\r\n\r\n<p><strong>A&eacute" +
            ";reo:</strong>&nbsp;Passagem a&eacute;rea de ida e volta entre a cidade de " +
            "origem e <strong>Rio de Janeiro</strong> - Aeroporto Internacional Tom Jobim " +
            "-&nbsp;Gale&atilde;o (GIG)&nbsp;ou&nbsp;o Aeroporto Santos-Dumont (SDU), em " +
            "classe econ&ocirc;mica, <strong>podendo haver conex&atilde;o e/ou escala" +
            "</strong>.</p>\r\n\r\n<ul>\r\n\t<li><strong>Bagagem:</strong> Este pacote " +
            "permite levar uma bagagem de m&atilde;o com at&eacute; 10 quilos. Desta forma," +
            " voc&ecirc; poder&aacute; levar uma mochila ou bolsa (que dever&aacute; " +
            "ser acomodada debaixo do seu assento) e uma bagagem de m&atilde;o " +
            "(que dever&aacute; caber no compartimento superior do avi&atilde;o)." +
            "</li>\r\n</ul>\r\n\r\n<p><strong>Hospedagem:&nbsp;</strong>Em<strong>&nbsp" +
            ";Paraty&nbsp;</strong>na Pousada Serra da Bocaina, Pousada Ciclo do Ouro," +
            " Pousada da Chacara Paraty&nbsp;ou outra pousada de categoria&nbsp;econ&ocirc" +
            ";mica -&nbsp;com caf&eacute; da manh&atilde;.</p>\r\n\r\n<p>A hospedagem " +
            "ser&aacute; definida pelo Hurb de acordo com a disponibilidade e tarif&aacute" +
            ";rio promocional.</p>\r\n\r\n<p><strong>Aten&ccedil;&atilde;o:</strong> As " +
            "di&aacute;rias s&atilde;o contabilizadas pelas noites dormidas a partir da sua " +
            "chegada no hotel.</p>\r\n\r\n<ul>\r\n</ul>\r\n\r\n<p><strong>Idiomas do hotel" +
            ":</strong> Portugu&ecirc;s, ingl&ecirc;s ou espanhol</p>\r\n\r\n<p><strong>" +
            "Incluso:&nbsp;</strong>Passeio de Escuna.</p>\r\n\r\n<p><strong>Aluguel de " +
            "Carro Opcional: </strong>Autom&oacute;vel categoria econ&ocirc;mica, " +
            "incluindo km livre e CDW (as taxas e impostos locais de loca&ccedil;&atilde;o" +
            " do ve&iacute;culo dever&atilde;o ser pagas no momento da retirada do mesmo)" +
            ".</p>\r\n\r\n<p>O locat&aacute;rio/condutor dever&aacute; possuir idade " +
            "m&iacute;nima de 25 anos;</p>\r\n\r\n<p>O locat&aacute;rio dever&aacute;" +
            " apresentar:&nbsp;</p>\r\n\r\n<ul>\r\n\t<li>\r\n\t<p>documento de habilita" +
            "&ccedil;&atilde;o original, emitido no Brasil, v&aacute;lido e dentro do " +
            "prazo de vencimento + PID (permiss&atilde;o internacional de dire&ccedil;" +
            "&atilde;o)&nbsp; Emitido no Brasil;</p>\r\n\t</li>\r\n\t<li>\r\n\t<p>&nbsp" +
            ";passaporte original e v&aacute;lido;</p>\r\n\t</li>\r\n\t<li>\r\n\t<p>&nbsp" +
            ";cart&atilde;o de cr&eacute;dito internacional v&aacute;lido, de sua " +
            "titularidade, dentro do prazo de vencimento, emitido por uma institui&ccedil" +
            ";&atilde;o banc&aacute;ria e com limite de cr&eacute;dito dispon&iacute;vel" +
            " para pr&eacute;-autoriza&ccedil;&atilde;o da cau&ccedil;&atilde;o de garantia." +
            " N&atilde;o ser&atilde;o aceitos cart&otilde;es de cr&eacute;dito de " +
            "terceiros.</p>\r\n\t</li>\r\n</ul>\r\n\r\n<p>&Eacute; necess&aacute;rio" +
            " indicar o nome completo do viajante que ser&aacute; o locat&aacute;rio e " +
            "condutor no campo Solicita&ccedil;&otilde;es Especiais do formul&aacute;" +
            "rio de agendamento.</p>\r\n\r\n<p>&nbsp;</p>\r\n",
        addressData = AddressData(city = "Paraty", country = "Brasil"),
        galleryData = GalleryData(
            images = listOf(
                ImageData(
                    url = "https://s3.amazonaws.com/legado-prod/prod/ofertas/imagens" +
                        "/2021/07/22/16/26/paraty1.jpg",
                    description = "Imagem do Centro Histórico de Paraty com as ruas" +
                        " parcialmente inundadas"
                )
            )
        )
    )
}
