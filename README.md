# 🚀 Take Home Test - Github Gist & Reqres API

Proyek ini merupakan framework *automation testing*  komprehensif yang mencakup dua lapisan utama UI *(Frontend)* untuk skenario CRUD pada **[Github Gist](https://gist.github.com/)** dan API *(Backend)* untuk manipulasi data pada **[Reqres](https://reqres.in)**.

Proyek ini dikerjakan memenuhi syarat penyaluran kerja **assignment Take Home Test**. Silakan klik link berikut untuk melihat **[QA Report (Documentation)](https://docs.google.com/document/d/1YVM-QQ-tTa0p5GDegRADkjd2U4UePFnBJRrTiTmv1wo/edit?usp=sharing)**.


---

## 🛠️ Teknologi & Library yang Digunakan

* **Bahasa Pemrograman:** Java 21
* **Build Tool:** Gradle / Maven
* **Testing Framework:** TestNG
* **UI Automation:** Selenium WebDriver
* **API Automation:** RestAssured (RESTful API Validation)
* **Reporting:** ExtentReports (Spark Reporter)
* **Design Pattern:** Page Object Model (POM) & Service Object Model (SOM)

---

## 📁 Struktur Proyek (Page Object Model)

Proyek ini dibangun menggunakan konsep **Page Object Model (POM)** untuk memisahkan antara logika pengujian, elemen halaman (*locators*), dan setup framework agar lebih modular.

```text
QA-Automation-TakeHomeTest/
├── reports/                 # Folder hasil laporan otomatis
│   ├── API_Report.html      # Laporan lengkap eksekusi API
│   ├── UI_Report.html       # Laporan lengkap eksekusi UI
│   └── screenshots/         # Direktori dinamis untuk bukti screenshot jika UI Test gagal
├── src/test/java/
│   ├── api/                 # Arsitektur API Testing (Reqres)
│   │   ├── base/            # Setup konfigurasi global & Header Injections
│   │   ├── services/        # Service layer untuk HTTP Methods (GET, POST, PUT, DELETE)
│   │   └── tests/           # Skenario pengujian (UserTest)
│   ├── ui/                  # Arsitektur UI Testing (GitHub Gist)
│   │   ├── base/            # Setup siklus WebDriver
│   │   ├── pages/           # Elemen Locators & Actions (LoginPage, GistDetailPage, dll)
│   │   └── tests/           # Eksekusi End-to-End Test (GistCRUDTest)
│   └── utils/               # Alat bantu (ConfigReader, Extent UI/API, Listeners)
├── staging.properties       # File penyimpan variabel rahasia (Kredensial & API Key)
├── suites                   # File TestNG UI & API
└── README.md                # Dokumentasi panduan proyek
```
---

## 🧪 Scenario Test
**UI TESTING (GitHub Gist)**

| **Fitur**                  |   **Status**    |                                               **Catatan**                                                |
|:---------------------------|:---------------:|:--------------------------------------------------------------------------------------------------------:|
| GET Users (List)           |        ✅        |               Berhasil autentikasi ke dalam akun GitHub dan masuk ke halaman beranda Gist.               |
| Create Public Gist         |        ✅        |     Memasukkan filename dinamis (dengan timestamp), mengisi code editor, dan menyimpan Gist Publik.      |
| Edit Existing Gist         |        ✅        |           Menemukan Gist yang baru saja dibuat, menekan tombol edit, dan mengubah isi kodenya.           |
| Delete Existing Gist       |        ✅        |                 Menghapus Gist secara permanen dan memvalidasi penanganan pop-up alert.                  |
| See List of Gists          |        ✅        | Memastikan direktori/profil pengguna terbuka dan memvalidasi bahwa Gist yang dihapus sudah tidak muncul. |
| **Total Skenario:**        | **5 ✅<br/>0 ❌** |                                      **Scope Coverage: Gist CRUD**                                       |

**API TESTING (Reqres.in)**

| **Fitur**           |   **Status**    |                                                           **Catatan**                                                           |
|:--------------------|:---------------:|:-------------------------------------------------------------------------------------------------------------------------------:|
| GET Users (List)    |        ✅        |                               Menampilkan daftar pengguna pada page=2. (Expected Status: 200 OK)                                |
| GET User by ID      |        ✅        |                            Menarik data spesifik pengguna berdasarkan ID. (Expected Status: 200 OK)                             |
| POST User           |        ✅        | Membuat pengguna baru (Payload: email & password) dan menangkap ID dinamis dari response server. (Expected Status: 201 Created) |
| PUT User            |        ✅        |                 Menggunakan ID dari langkah POST untuk memperbarui atribut pengguna. (Expected Status: 200 OK)                  |
| DELETE User         |        ✅        |                     Menghapus pengguna menggunakan ID dari langkah POST. (Expected Status: 204 No Content)                      |
| **Total Skenario:** | **5 ✅<br/>0 ❌** |                                           **Scope Coverage: Get, Post, Put & Delete**                                           |
---
## ⚙️ Persiapan Awal *(Prerequisites)*

Sebelum mulai nge-run proyek ini, pastikan sudah menginstall *tools* di bawah ini:
1.  **Java Development Kit (JDK):** Versi 21.
2.  **IDE:** IntelliJ IDEA.
3.  **Koneksi Internet:** Wajib ada, karena pengetesan langsung ke server [Github Gist](https://gist.github.com/) *live* [Reqres](https://reqres.in).
---

## 📥 Langkah Instalasi ***(Installation Steps)***
Ikuti langkah-langkah di bawah ini untuk mengunduh dan mengatur proyek di komputer

1. **Clone Repositori:** Buka Terminal/Command Prompt, arahkan ke folder tujuanmu, lalu jalankan perintah ini:
```text 
git clone https://github.com/diazaryanta/TakeHomeTest-QA.git
```
2. **Buka Proyek di IntelliJ IDEA:**
* Buka IntelliJ IDEA.
* Pilih menu **Open.**
* Cari dan pilih folder `TakeHomeTest-QA` yang baru saja di-clone.

3. **Sinkronisasi Gradle (Download Dependencies):**
* Biarkan IDE memuat file `build.gradle` atau `pom.xml` untuk mengunduh library Selenium, Rest-Assured, TestNG, dan ExtentReports secara otomatis.

4. **Konfigurasi Kredensial:**
* Buka file `src/test/resources/config/staging.properties`
* Pastikan URL, Email, dan Password untuk akun [Github Gist](https://gist.github.com/) sudah terisi dengan benar.
* Isi variabel kredensial Anda yang sah.
``` Text
# Kredensial UI (GitHub)
ui.base.url=https://gist.github.com
github.username=YOUR_GITHUB_USERNAME
github.password=YOUR_GITHUB_PASSWORD

# Kredensial API (Reqres)
api.base.url=https://reqres.in/api
api.key=reqres_7f1036a9f4cb4750bb46641db6ba76ff
```
---

## 🚀 Cara Menjalankan Test ***(How to Run)***

### Melalui TestNG Suite XML (Direkomendasikan)
Cara paling rapi untuk menjalankan keseluruhan kumpulan pengujian:
1. Buka folder project explorer di sebelah kiri.
2. Untuk menguji UI: Klik kanan pada `file testng-ui.xml` -> Pilih Run.
3. Untuk menguji API: Klik kanan pada `file testng-api.xml` -> Pilih Run.
---
## 🧠 Fitur Utama & Keunggulan Framework

* ***Chained E2E Testing:*** Menggunakan arsitektur TestNG `dependsOnMethods` untuk UI. Jika proses pembuatan data gagal, skenario *Edit/Delete* akan otomatis di-skip *(Fail Fast)* agar resource tidak terbuang.
* ***Automatic Screenshot:*** Untuk pengujian UI, sistem akan otomatis mengambil screenshot jika terjadi kegagalan (Failure) dan melampirkannya ke dalam Extent Report.
* ***Smart API Authentication:*** Injeksi API Key dinamis di *Base Level*. Token dibaca dari *properties* dan otomatis disisipkan ke *Header Request* di seluruh *endpoint* Reqres tanpa perlu repot *hardcode*.
* ***Dual Independent Reporting:*** Laporan hasil pengujian diekstrak ke dalam dua file HTML terpisah (`UI_Report.html` dan `API_Report.html`) agar lebih analitis dan fokus.
---
## 📈 Cara Lihat Report
Setelah test selesai dijalankan lewat Terminal, sistem akan otomatis bikin laporan yang rapi.
* **UI Report:** `reports/UI_Report.html`.
* **API Report:**`reports/API_Report.html`.

---
## 👨🏻‍💻 Tester
**Diaz**