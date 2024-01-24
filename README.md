# ✨ Lalu Lelang


Lalu lelang is an auction mobile (android) app that enable users to place a bid on various items that are being auctioned.


This project is built using Clean Architecture and follows the MVVM design pattern to ensure a robust and maintainable codebase. Key tools and libraries include Hilt Dagger for dependency injection, Retrofit for API calls, and Glide for efficient image loading.

## Features

1. Login / register account
2. View available auctioned items
3. Place a bid on an auctioned items
4. View all the created bids, including the bid status
5. Make a delivery request for the winning bid

## Implemented concepts and technologies

- **Clean Architecture:** Organized codebase for better maintainability and scalability.
- **MVVM Design Pattern:** Separation of concerns for improved testability and readability.
- **Hilt Dagger:** Dependency injection for better modularization and flexibility.
- **Retrofit:** Efficient networking library for seamless API calls.
- **Glide:** Image loading library for smooth display of images from external sources.

## Screenshots

![Showcase](https://github.com/rizadwiandhika/LaluLelangMobile/assets/68406409/b76123c1-23e5-422f-89c7-9307652635a3)
![Showcase 2](https://github.com/rizadwiandhika/LaluLelangMobile/assets/68406409/ddf3975e-d435-46e5-a24f-1de955fbc0e0)

## Getting started

1. Clone the repository:
   ```
   git clone https://github.com/rizadwiandhika/LaluLelangMobile.git
   ```

2. Connect computer to the wifi/hotspot and view the assigned IP address, set the value into [this part](https://github.com/rizadwiandhika/LaluLelangMobile/blob/65dd2f5678deb459e677d309ef8ecd1d171b4c4e/app/src/main/java/com/rizadwi/mandiri/android/lalulelang/module/NetworkModule.kt#L32)
   
   ![image](https://github.com/rizadwiandhika/LaluLelangMobile/assets/68406409/7d8e6cd3-3456-4517-957f-27d5fca5db84)
   ![image](https://github.com/rizadwiandhika/LaluLelangMobile/assets/68406409/90ff2416-c1b6-498a-808c-8841dbb78413)
   
3. Start local api server using docker
   ```
   curl https://gist.githubusercontent.com/rizadwiandhika/ab244d8853f24683d45bc2a569807234/raw/bcc5251b52161b6bc407f0d23b80d7cb6f303504/docker-compose.yml > docker-compose.yml && docker compose up
   ```

4. Run the app using Android Studio

## Contributors

- Riza Dwi Andhika
