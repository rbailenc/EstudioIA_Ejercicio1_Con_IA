# EstudioIA - Ejercicio 1 con IA

Aplicación Android desarrollada en Kotlin utilizando Jetpack Compose, que consume la PokeAPI para mostrar un listado de Pokémon y su información detallada.

Este proyecto forma parte de un ejercicio práctico enfocado en el uso de buenas prácticas de arquitectura, consumo de APIs REST y desarrollo moderno en Android.

---

# Descripción del Proyecto

La aplicación permite:

- Consultar un listado de Pokémon desde una API pública  
- Visualizar información detallada de cada Pokémon  
- Mostrar imágenes oficiales  
- Aplicar arquitectura limpia (Clean Architecture)  
- Separación clara de responsabilidades por capas  

---

# Arquitectura

El proyecto sigue los principios de Clean Architecture + MVVM, organizando el código en capas desacopladas.

## Capas del proyecto

```
app/
 ├── data/
 ├── domain/
 ├── ui/
 ├── di/
```

### 1. Capa Data

Responsable de:

- Modelos DTO
- Consumo de API (Retrofit)
- Implementación del repositorio
- Mappers (DTO → Modelo de dominio)

Ubicación:

```
data/
 ├── model/
 ├── mapper/
 ├── Nerwork/
 ├── Repository/
```

---

### 2. Capa Domain

Contiene:

- Modelos de negocio
- Interfaces del repositorio
- Casos de uso (UseCase)

Ubicación:

```
domain/
 ├── model/
 ├── repository/
 ├── usecase/
```

---

### 3. Capa UI

Contiene:

- Pantallas (Screens)
- ViewModels
- Componentes reutilizables
- Tema (Material 3)

Ubicación:

```
ui/
 ├── screens/
 ├── components/
 ├── viewmodels/
 ├── theme/
```

---

### 4. Inyección de Dependencias

Implementada con Hilt.

Ubicación:

```
di/
 ├── NetworkModule.kt
 ├── RepositoryModule.kt
```

---

# Flujo de la aplicación

1. La UI solicita datos al ViewModel.
2. El ViewModel ejecuta un UseCase.
3. El UseCase consulta al Repository.
4. El Repository obtiene datos desde la API.
5. Los datos se transforman mediante un Mapper.
6. Se devuelven modelos de dominio a la UI.

---

# Tecnologías Utilizadas

- Kotlin
- Jetpack Compose
- Material 3
- MVVM
- Clean Architecture
- Hilt (Dependency Injection)
- Retrofit
- Gson
- Coil (carga de imágenes)
- Gradle Kotlin DSL

---

# API Utilizada

Se utiliza la API pública:

https://pokeapi.co/

Ejemplo de endpoints:

```
GET https://pokeapi.co/api/v2/pokemon
GET https://pokeapi.co/api/v2/pokemon/{name}
```

---

# Instalación y Ejecución

## Requisitos

- Android Studio (última versión recomendada)
- JDK 17 o superior
- SDK Android configurado
- Conexión a Internet

---

## Clonar el proyecto

```bash
git clone https://github.com/rbailenc/EstudioIA_Ejercicio_1_Con_IA.git
```

---

## Ejecutar

1. Abrir el proyecto en Android Studio
2. Sincronizar Gradle
3. Ejecutar en un emulador o dispositivo físico

---

# Testing

El proyecto incluye:

- Unit Tests (`test/`)
- Instrumented Tests (`androidTest/`)

Para ejecutarlos:

- Click derecho sobre el módulo → Run Tests

---

# Estructura Técnica Detallada

## Modelos DTO

Representan la respuesta cruda de la API.

Ejemplo:

- `PokemonDto`
- `PokemonDetailDto`
- `AbilityDto`
- `SpritesDto`

---

## Mappers

Transforman DTO → Modelo de dominio.

Ejemplo:

```
PokemonMapper.kt
```

---

## Use Case

Encapsula la lógica de negocio.

Ejemplo:

```
GetPokemonUseCase.kt
```

---

## UI

Pantallas principales:

- `PantallaInicio`
- `PantallaDetallePokemon`

ViewModels:

- `PantallaPrincipalViewmodel`
- `PantallaDetalleViewModel`

---

# Posibles Mejoras Futuras

- Búsqueda de Pokémon
- Paginación
- Caché local con Room
- Modo oscuro dinámico
- Animaciones entre pantallas
- Swipe to refresh

---

# Video
Se encuentra en el propio proyecto




# Autor

Proyecto desarrollado por rbailenc.



