# ErzTask
ErzTask
ErzTask, modern yazılım geliştirme prensiplerine uygun bir şekilde tasarlanmış bir görev yönetimi mobil uygulamasıdır.

Proje Açıklaması
- Projemiz Kotlin Programlama Dili ile yazılmıştır.
- Firebase Authtication, Firebase Firestore kullanılmıştır.
- Uygulama giriş ekranı ile karşılamaktadır. Admin bilgileri daha önce paylaşılmıştır. Admin kişisi şirket içierisinde yeni profiller ve kullanıcılar oluşturur.
- Uygulama içerisinde kullanıcılar giriş yaptıklarında bir menu karşılamaktadır.(Admin için ayrı bir ekran tasarlanmıştır.) Bu menü aracılığıyla;
- - Mevcut yapılmayı bekleyen görevleri ve detaylarını görebilirler.
  - Tamamlanmış görevleri ve detaylarını görebilirler.
  - Diğer kullanıcıları ve profil detaylarını görebilirler.
  - Sohbet odasında diğer kullanıcılar ile sohbet edebilirler.
  - Kendi Profillerini ve detaylarını görebilirler.
  - Görevler oluşturabilirler.(Kulalnıcıların ünvan bilgisi bulundukları takımın kaptanı ise yeni görevler oluşturabilir.)
- Projedeki amaç şirketlerin kendi çalışanları ile iletişim kurmaları, görevler vermeyi ve onları takip etmeyi kolaylaştıran bir uygulama yapmaktır.

Özellikler
- Görev ekleme, düzenleme ve silme
- Görevlerin son tarihlerini belirleme
- Görevlerin tamamlanma durumlarını takip etme
- Kullanıcı dostu ve sade bir arayüz
- Fragment tabanlı navigasyon

Teknik Detaylar
MVVM (Model-View-ViewModel) Mimarisi:
Proje, MVVM mimarisi kullanılarak geliştirildi. Bu yapı, uygulamanın daha modüler, test edilebilir ve sürdürülebilir olmasını sağlar.

SOLID Prensipleri:
Kod, Single Responsibility, Open-Closed, Liskov Substitution, Interface Segregation ve Dependency Inversion prensiplerine uygun olarak yazılmıştır.

Android Jetpack Navigation Component:
Fragment geçişleri ve kullanıcı gezintisi, Jetpack'in Navigation Component’i kullanılarak kolaylaştırıldı. Bu sayede, navigasyon işlemleri daha düzenli ve merkezi bir şekilde yönetildi.

Kotlin:
Uygulama tamamen Kotlin programlama dili ile geliştirilmiştir.

LiveData ve ViewModel:
ViewModel, UI ile veri kaynağı arasındaki iletişimi yönetirken, LiveData ile UI’nın veri değişikliklerine duyarlı olması sağlanmıştır.

Repository Pattern:
Veriler, Repository deseni kullanılarak yönetilmiştir ve veri kaynaklarının yönetimi soyutlanmıştır.

Kullanım
Görev Ekleme:
Ana menüdeki "Yeni Görev Ekle" butonuna basarak yeni bir görev ekleyebilirsiniz.

Görev Güncelleme:
Görev listesinde bir göreve tıkladığınızda, görevi düzenleyebilir veya silebilirsiniz.

Görev Durumu:
Görevleri tamamlandı olarak işaretleyebilir ve bu durumları takip edebilirsiniz.

Proje Mimarisi

Model: Veri sınıflarını ve iş mantığını içerir.
ViewModel: Verilerin hazırlanmasından ve UI ile veri kaynağı arasındaki iletişimden sorumludur.
View: Kullanıcı arayüzünü temsil eder ve kullanıcı girişlerini işleyerek ViewModel’e iletir.
Navigation Component: Kullanıcı gezintisi, Navigation Graph kullanılarak yönetilmiştir.

Kurulum
- Bu projeyi klonlayın:
"git clone https://github.com/ahmetBugra25/ErzTask.git"
- Android Studio ile projeyi açın.
- Gerekli bağımlılıkların indirilmesini bekleyin.
- Uygulamayı bir emülatörde veya gerçek cihazda çalıştırın.

Katkıda Bulunma
- Her türlü katkıya açığız. Yeni özellikler eklemek veya hataları düzeltmek için bir Pull Request oluşturabilirsiniz.
