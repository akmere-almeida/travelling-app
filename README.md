![Build and Test](https://github.com/akmere-almeida/travelling-app/actions/workflows/integration.yml/badge.svg) | ![Coverage](.github/badges/jacoco.svg)
> :warning: **Este projeto implementa o JetPack Compose**: Siga o [Script](#script) para
> roda-lo corretamente!

# Softwares de terceiros utilizados
- Cliente GraphQL com ([Apollo](https://www.apollographql.com/))
- Cliente Http com ([OkHttp](https://square.github.io/okhttp/))

# Primeiros passos

### Script
1. Baixe este repositório, extraia os arquivos e abra pelo Android Studio
2. Verifique se seu Android Studio é compatível com a versão do AGP, caso contrário baixe a versão [mais recente](https://developer.android.com/studio)
3. Rode no terminal o comando `./gradlew build` para montar a aplicação
4. Rode `./gradlew jacocoAndroidCoverageVerification` para rodar os testes unitários e gerar cobertura de teste
5. Ligue o emulador e em seguinda rode `./gradlew jacocoAndroidTestReport` para rodar os testes Instrumentados
   e gerar os relatórios de cobertura de teste.
6. Pelo Android Studio, execute a aplicação.

# Notas
- Este projeto implementa o JetPack Compose, habilitado dentro de `app/build.gradle.kts`. 
  Portanto, para melhorar a experiência ao utiliza-lo, você deve baixar a última [versão _canary_](https://developer.android.com/studio/preview) do
  Android Studio Preview.
- A versão do Gradle e a versão do Plug-in do Android para Gradle utilizada é a *_7.2_* e *_7.0.2_*, respectivamente.
  Para mais informações acesse [AGP](https://developer.android.com/studio/releases/gradle-plugin)
- Este projeto contém `.github/workflows` para de análise de código, cobertura de testes, testes com JUnit e testes Instrumentados.
