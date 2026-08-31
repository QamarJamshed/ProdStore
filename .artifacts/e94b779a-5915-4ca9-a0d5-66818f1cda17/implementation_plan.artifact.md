# Implementation Plan - Product Details Feature

This plan outlines the implementation of the Product Details feature, following the established Clean Architecture, Pragmatic MVI, and Navigation 3 patterns.

## User Review Required

> [!IMPORTANT]
> **No UseCase:** I will skip creating a `GetProductDetailsUseCase` as it would only delegate to `ProductRepository.getProductById(id)`. The ViewModel will interact with the Repository abstraction directly, adhering to the project's lean-domain rule.

## Proposed Changes

### 1. Product Details Feature (`productdetails-impl`)

#### [NEW] [ProductDetailsModule.kt](file:///C:/Users/Qamar/AndroidStudioProjects/EcommerceApp/feature/productdetails/productdetails-impl/src/main/java/com/example/productdetails_impl/di/ProductDetailsModule.kt)
- Register `ProductDetailsViewModel` in Koin.
- ViewModel will be defined with a parameter for `productId`.

#### [NEW] [ProductDetailsState.kt](file:///C:/Users/Qamar/AndroidStudioProjects/EcommerceApp/feature/productdetails/productdetails-impl/src/main/java/com/example/productdetails_impl/presentation/ProductDetailsState.kt)
- `isLoading: Boolean = false`
- `product: Product? = null`
- `error: String? = null`

#### [NEW] [ProductDetailsIntent.kt](file:///C:/Users/Qamar/AndroidStudioProjects/EcommerceApp/feature/productdetails/productdetails-impl/src/main/java/com/example/productdetails_impl/presentation/ProductDetailsIntent.kt)
- `ToggleWishlist(product: Product)`
- `Retry`

#### [NEW] [ProductDetailsViewModel.kt](file:///C:/Users/Qamar/AndroidStudioProjects/EcommerceApp/feature/productdetails/productdetails-impl/src/main/java/com/example/productdetails_impl/presentation/ProductDetailsViewModel.kt)
- Accept `productId: Int` and `ProductRepository` in constructor.
- Fetch product details on `init`.
- Handle `ToggleWishlist` and `Retry` intents.

#### [NEW] [ProductDetailsScreen.kt](file:///C:/Users/Qamar/AndroidStudioProjects/EcommerceApp/feature/productdetails/productdetails-impl/src/main/java/com/example/productdetails_impl/presentation/ProductDetailsScreen.kt)
- Use `Scaffold` with a `TopAppBar` (back button and title).
- Display product image using `AsyncImage`.
- Display title, price, rating, category, and description with M3 typography.
- Floating Action Button or Top Bar action for Wishlist toggle.

#### [NEW] [ProductDetailsEntry.kt](file:///C:/Users/Qamar/AndroidStudioProjects/EcommerceApp/feature/productdetails/productdetails-impl/src/main/java/com/example/productdetails_impl/navigation/ProductDetailsEntry.kt)
- Navigation 3 `EntryProviderScope` extension for `ProductDetailsRoute`.
- Pass `productId` from the route to `koinViewModel`.

---

### 2. Design System Reused

- **Typography:** `headlineMedium` for title, `titleLarge` for price, `bodyMedium` for description.
- **Components:** Reuse `RatingBadge` (if applicable) and standard M3 components.
- **Images:** Use Coil 3's `AsyncImage` with `ContentScale.Fit` or `Crop` as appropriate.

---

### 3. App Integration

#### [MODIFY] [App.kt](file:///C:/Users/Qamar/AndroidStudioProjects/EcommerceApp/app/src/main/java/com/example/ecommerceapp/App.kt)
- Register `productDetailsModule` in Koin.

#### [MODIFY] [MainApp.kt](file:///C:/Users/Qamar/AndroidStudioProjects/EcommerceApp/app/src/main/java/com/example/ecommerceapp/MainApp.kt)
- Replace `ProductDetailsRoute` placeholder with `productDetailsEntry`.
- Handle `onBack` navigation.

#### [MODIFY] [build.gradle.kts (app)](file:///C:/Users/Qamar/AndroidStudioProjects/EcommerceApp/app/build.gradle.kts)
- Add implementation dependency for `:feature:productdetails:productdetails-impl`.

## Verification Plan

### Automated Tests
- Static analysis: Ensure all modules compile and dependencies are correct.
- `gradlew :feature:productdetails:productdetails-impl:assembleDebug`.

### Manual Verification
1. Open App -> Onboarding -> Home.
2. Tap a Product Card in the grid.
3. Verify Product Details screen opens with correct data.
4. Verify Loading state appears during fetch.
5. Verify Error state/Retry works (simulate by disconnecting network).
6. Verify Back button returns to Home grid correctly.
7. Verify Wishlist toggle reflects state (requires logic in ViewModel).
