- Projede Google Fitness API ve Firebase teknolojilerini kullandim.
- Uygulamayi Kotlin yazilim dili ile gelistirdim.
- Kod dosyalarini duzenlemek icin data, di(dependency injection) ve ui klasorlerini kullandim.
- Dependency injection olarak Dagger Hilt kullandim
- Navigation component kullanarak fragmentleri ve ekranlar arasi gecisleri yonettim.
- UI elementleri icin Material Design prensiplerini ve view'larini kullandim.

- Oncelikle proje yapisini olusturdum ve ekran tasarimini tamamladim.
- Daha sonra Google Cloud Console uzerinde yeni bir Android projesi olusturdum.
- Projem icin Fitness API'yi etkinlestirdim.
- Projeme ait package id, SHA-1 key gibi gerekli bilgileri vererek Projem icin Credentials olusturdum, gerekli bilgileri projeme ekledim.
- OAuth consent (kullanicinin Google mail adresi ile giris yaptigi dialog ekrani) icin gerekli bilgileri tamamladim ve uygulama icin test user'lari ekledim. Diger turlu uygulamanin yayinlanmasi ve verify edilmesi gerekiyor, bu sebeple test user'lari ekledim. Sizin giris yapacaginiz kullanici test user'i olarak ekli olmadigi icin adim verilerini goremeyebilirsiniz.
- Google DOkumanlarini okuyarak Google Fitness SDK'yi Android projeme entegre ettim ve kullanicinin data'larini almaya basladim. Fakat kullanicnin sadece adim sayisini okuyabildim.
- Kullanicinin gunluk, haftlik, aylik ve yillik adim sayisi verilerini SDK uzerinden parametre girerek okuyabiliyorum, adim sayisini ekranda gosteririken tarih araligini da ekranda gosterdim.
- Fakat Fitness API gunluk, haftalik, aylik ve yillik icin surekli ayni adim sayisini veriyor, sebebini bulamadim.
- Ayrica Fitness API dun calisirken bugun veriler duzensiz gelmeye, hatta gelmemeye basladi. Sanirim Fitness API request limitine takiliyor. Dun cektigim ekran videolarina(video_1.mp4 ve video_2.mp4 dosyaları) goz atabilirsiniz.

- Projenin APK'sini ve ekran videolarini da githugb'a ekledim, kontrol edebilirsiniz.