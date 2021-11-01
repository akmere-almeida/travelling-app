![Build and Test](https://github.com/akmere-almeida/travelling-app/actions/workflows/integration.yml/badge.svg) | ![Coverage](.github/badges/jacoco.svg)
> :warning: **Este projeto implementa o JetPack Compose**: Siga o [Script](#script) para
> roda-lo corretamente!

# Softwares de terceiros utilizados
- Carregamento de imagens com ([Coil](https://github.com/coil-kt/coil))
- Cliente GraphQL com ([Apollo](https://www.apollographql.com/))
- Cliente Http com ([OkHttp](https://square.github.io/okhttp/))

## Features
Esta aplicação contém quatro telas: a Home com listas de ofertas em destaque e ofertas visualizadas,
uma tela de busca, uma tela de detalhes das ofertas onde é possível compartilhar,
favoritar e abrir a localização no mapa de uma oferta.
E uma tela de ofertas favoritadas.
A navegação para a tela de ofertas favoritadas é feita com o uso de um _BottomNavigation_ menu.

### Estrutura do App

Pacote [`com.akmere.travelling_app.presentation`][1]
[`ScreenNavigator.kt`][2] configura a navegação entre as telas e as ações no app.

[1]: app/src/main/java/com/akmere/travelling_app/presentation
[2]: app/src/main/java/com/akmere/travelling_app/presentation/common/components/ScreenNavigator.kt

### Home

Pacote [`com.akmere.travelling_app.presentation.screen.home`][3]

Esta tela exibe três listagens horizontais: 
* Filtros para busca (Hotel, Pacote)
* Ofertas em Destaque (ordenadas pela quantidade de favoritos)
* Ofertas Visualizadas (ordenadas de forma decrescente em que foram visualizadas )

esta tela exibe também uma barra para navegar à tela de busca.

[3]: app/src/main/java/com/akmere/travelling_app/presentation/screen/home

### Detalhes da Oferta
Pacote [`com.akmere.travelling_app.presentation.screen.offer_details`][4]

Esta tela exibe as informações detalhadas de uma oferta, além disso, é por ela que é possível favoritar uma oferta.
Uma animação é exibida ao favoritar/desfavoritar a oferta.

Outras funcionalidades disponíveis nesta tela são:
* Navegar para o mapa a partir do ícone de localização abaixo do título
* Compartilhar uma oferta e navegar de volta para o app a partir do link compartilhado

[4]: app/src/main/java/com/akmere/travelling_app/presentation/screen/offer_details

### Buscar por Ofertas
Pacote [`com.akmere.travelling_app.presentation.screen.search`][5]

Esta tela é exibida ao clicar na barra de busca na Home.
Nesta tela é possível buscar por sugestões de lugares (cidades, estados e países), ou filtrar por um termo qualquer.

[5]: app/src/main/java/com/akmere/travelling_app/presentation/screen/search

### Ofertas Favoritadas
Pacote [`com.akmere.travelling_app.presentation.screen.favorite`][6]

Esta tela exibe uma listagem com as ofertas favoritadas pelo usuário

[6]: app/src/main/java/com/akmere/travelling_app/presentation/screen/favorite

### Domínio
Pacote [`com.akmere.travelling_app.domain`][7]

O domínio consiste de Interactors que interagem com a camada de dados para executar ações específicas nos dados, ex:
* Ordernar uma coleção de ofertas
* Agregar ofertas por quantidades de favoritos/visualizadas
* Agregar ofertas de hóteis e pacotes em uma coleção única e ordená-la de forma decrescente

[7]: app/src/main/java/com/akmere/travelling_app/domain

### Dados
Pacote [`com.akmere.travelling_app.data`][8]

Esta camada é reponsável por conter a fonte da verdade sobre os dados. Ela consiste de Repositories e Services
que fazém a comunicação com as APIs externas e em memória:

APIs externas (GraphQL):
* [`com.akmere.travelling_app.data.service.OfferService`][9]
* [`com.akmere.travelling_app.data.service.SuggestionService`][10]
* [`com.akmere.travelling_app.data.repository.OfferRepository`][11]
* [`com.akmere.travelling_app.data.repository.SuggestionsRepository`][12]

Em memória:
* [`com.akmere.travelling_app.data.repository.FavoriteRepository`][13]
* [`com.akmere.travelling_app.data.repository.ViewedOfferRepository`][14]

[8]: app/src/main/java/com/akmere/travelling_app/data
[9]: app/src/main/java/com/akmere/travelling_app/data/service/OfferService.kt
[10]: app/src/main/java/com/akmere/travelling_app/data/service/SuggestionService.kt
[11]: app/src/main/java/com/akmere/travelling_app/data/repository/OfferRepository.kt
[12]: app/src/main/java/com/akmere/travelling_app/data/repository/SuggestionsRepository.kt
[13]: app/src/main/java/com/akmere/travelling_app/data/repository/FavoriteRepository.kt
[14]: app/src/main/java/com/akmere/travelling_app/data/repository/ViewedOfferRepository.kt

### Injeção de Dependências

Pacote [`com.akmere.travelling_app.dependencies`][15]

Por último mas não menos importante. 
A injeção de dependências é feita de forma manual
no singleton [`com.akmere.travelling_app.dependencies.AppDependencies`][16]
e inicializada na [`com.akmere.travelling_app.MainActivity`][17]

[15]: app/src/main/java/com/akmere/travelling_app/dependencies
[16]: app/src/main/java/com/akmere/travelling_app/dependencies/AppDependencies.kt
[17]: app/src/main/java/com/akmere/travelling_app/MainActivity.kt

# Techstack
- Jetpack Compose
- Kotlin Coroutines
- Testes unitários com MockK

# Primeiros passos

### Script
1. Baixe este repositório, extraia os arquivos e abra pelo Android Studio
2. Verifique se seu Android Studio é compatível com a versão do AGP, caso contrário baixe a versão [mais recente](https://developer.android.com/studio)
3. Rode no terminal o comando `./gradlew build` para montar a aplicação
4. Rode `./gradlew jacocoAndroidCoverageVerification` para rodar os testes unitários e gerar cobertura de teste
~5. Ligue o emulador e em seguinda rode `./gradlew jacocoAndroidTestReport` para rodar os testes Instrumentados
   e gerar os relatórios de cobertura de teste.~
6. Pelo Android Studio, execute a aplicação.

# Notas
- Este projeto implementa o JetPack Compose, habilitado dentro de `app/build.gradle.kts`. 
  Portanto, para melhorar a experiência ao utiliza-lo, você deve baixar a última [versão _canary_](https://developer.android.com/studio/preview) do
  Android Studio Preview.
- A versão do Gradle e a versão do Plug-in do Android para Gradle utilizada é a *_7.2_* e *_7.0.2_*, respectivamente.
  Para mais informações acesse [AGP](https://developer.android.com/studio/releases/gradle-plugin)
- Este projeto contém `.github/workflows` para de análise de código, cobertura de testes, testes com JUnit e testes Instrumentados.
