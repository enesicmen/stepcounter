- Projede Google Fitness API ve Firebase teknolojilerini kullandım.
- Uygulamayı Kotlin yazılım dili ile geliştirdim.
- Kod dosyalarını düzenlemek için data, di(dependency injection) ve ui klasörlerini kullandım.
- Dependency injection olarak Dagger Hilt kullandım.
- Navigation component kullanarak fragmentleri ve ekranlar arasi geçişleri yönettim.
- UI elementleri için Material Design prensiplerini ve view'larını kullandım.

- Öncelikle proje yapısını oluşturdum ve ekran tasarımını tamamladım.
- Daha sonra Google Cloud Console üzerinde yeni bir Android projesi oluşturdum.
- Projem icin Fitness API'yi etkinleştirdim.
- Projeme ait package id, SHA-1 key gibi gerekli bilgileri vererek Projem için Credentials oluşturdum, gerekli bilgileri projeme ekledim.
- OAuth consent (kullanıcıniın Google mail adresi ile giriş yaptığiı dialog ekranı) için gerekli bilgileri tamamladım ve uygulama için test user'lari ekledim. Diğer türlü uygulamanın yayınlanması ve verify edilmesi gerekiyor, bu sebeple test user'ları ekledim. Sizin giriş yapacağınız kullanıcı test user'ı olarak ekli olmadiığı için adım verilerini göremeyebilirsiniz.
- Google Dökumanlarını okuyarak Google Fitness SDK'yı Android projeme entegre ettim ve kullanıcının data'larını almaya başladim. Fakat kullanıcının sadece adım sayısını okuyabildim.
- Kullanıcının günlük, hafatliık, aylık ve yıllık adım sayısı verilerini SDK uüzerinden parametre girerek okuyabiliyorum, adım sayısını ekranda gösteririken tarih aralığinı da ekranda gösterdim.
- Fakat Fitness API günlük, haftalık, aylık ve yıllık için sürekli aynı adım sayısını veriyor, sebebini bulamadım.
- Ayrıca Fitness API dün çalışırken bugün veriler düzensiz gelmeye, hatta gelmemeye basladı. Sanırım Fitness API request limitine takılıyor. Dün çektigim ekran videolarına(video_1.mp4 ve video_2.mp4 dosyaları) göz atabilirsiniz.

- Projenin APK'sını ve ekran videolarını da github'a ekledim, kontrol edebilirsiniz.