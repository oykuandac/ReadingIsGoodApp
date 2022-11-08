# ReadingIsGood


## Description

ReadingIsGood, internet üzerinden kitap siparişi verilebilen ve stok takibi yapılmasını sağlayan bir uygulamadır.

## Tech Stack

Java 11  
Spring Boot  
H2 Database  
JUnit 5  
Spring Security (Jwt Authentication)  
Maven  
Swagger  

## Security

JWT Authentication kullanılarak güvenlik sağlanmıştır.  

#### Credentials
username: readingisgood  
password: readingisgood12345

## Set Up

Ben Windows 10 kullandığımdan dolayı Docker'ı çalıştıramadım. Son derece yavaştı ve 1 gün boyunca uğraşmama rağmen sonuç alamadım yavaşlıktan dolayı hiç image build edemedim. Ancak yine de olması gerektiğini düşündüğüm şekilde Dockerfile ve docker-compose.yaml dosyalarını oluşturdum. 

Bunun dışında mvn spring-boot:run komutu ile proje başlatılabilir.

## Controller
<ul>![Paging sayfa2](https://user-images.githubusercontent.com/38913989/200575274-b87bb7da-f643-4e86-af1b-40b05d0d944b.PNG)

<li>
  AuthenticationController:<br />   
   - Bearer token üretme
 </li>
<li>
  CustomerController: <br />  
   - Yeni müşteri oluşturma
 </li>
  <li>
  BookController:  <br /> 
   - Yeni kitap oluşturma
 </li>
 <li>
  OrderController:  <br /> 
  - Yeni bir sipariş oluşturma,   <br /> 
  - Belirli bir müşterinin siparişlerini listeleme,   <br /> 
  - Belirli bir siparişin detaylarını sorgulama,   <br />  
  - Belirli iki tarih arasındaki siparişleri sorgulama   <br /> 
 </li>
   <li>
  StatisticsController:  <br /> 
   - Belirli bir müşteriye ait ve aylara göre gruplandırılmış sipariş istatistiklerini sorgulama
 </li>
</ul>

## Unit Test

Test için JUnit 5 kullanıldı ve oldukça kapsayıcı bir test yazıldı. Coverage Junit ile hesaplandığında %80.6 olarak hesaplanmıştır. SonarQube ile hesaplanırsa bir miktar farklılık gösterebilir.

![Coverage 80 6](https://user-images.githubusercontent.com/38913989/200536920-bb519b72-21e5-4841-a4be-18e15447e4e5.PNG)


## Process

1) Öncelikle bir JWT token oluşturulur. Bunun için <b> Credentials</b> bölümünde verilen username ve password ile, <b>  /auth/login</b> endpointine istek atılır.

![JWT Token Authentication](https://user-images.githubusercontent.com/38913989/200532034-1c5946a0-e0de-4c33-81da-d08c3857c8db.PNG)  

2) Dönen token ile <b> /customer/createCustomer/</b> endpointine istek atarak müşteri oluşturulur.
![Jwt token ile müşteri oluşturulur](https://user-images.githubusercontent.com/38913989/200532695-0e0a25a1-ed19-46cc-a049-6dc13dd7b9e2.PNG)
![Müşteri oluşturuldu](https://user-images.githubusercontent.com/38913989/200532819-7509e439-a705-4089-82c4-b11853f1e36a.PNG)

3) <b> /book/createBook/</b> endpointine istek atarak kitap oluşturulur. <b> Stok: 150 </b>  
![Kitap oluşturuldu](https://user-images.githubusercontent.com/38913989/200533076-dda63bf9-e452-4e21-8d58-574a79e9a869.PNG)

4) Oluşturulan müşterinin, oluşturulan kitabı sipariş etmesi sağlanır. <b>/order/createOrder</b> endpointine istek atılır. 1 numaralı müşteri, 1 numaralı kitaptan 3 adet sipariş eder, <b>stok 147</b> olarak güncellenir.   
Response içerisinde sipariş numarası, müşteri bilgileri ve sipariş detayları görüntülenebilmektedir.
![Sipariş oluşturuldu](https://user-images.githubusercontent.com/38913989/200533471-2372a63b-1d7c-4471-8946-87c410eca25d.PNG)

5) <b>/order/getOrder/{sipariş numarası}</b> endpointine istek atılarak belirli bir sipariş numarasına ait sipariş sorgulanabilir.
![Sipariş id'ye göre sorgulama](https://user-images.githubusercontent.com/38913989/200533792-059b896b-48e0-4837-8825-b1a4d23d1666.PNG)

6) Belirli bir müşteriye ait siparişler paging yapısı ile listelenebilir. <b>/customer/listOrdersOfCustomer/{müşteri numarasi}/{sayfa numarası}/{kayıt sayısı}</b> endpointine istek atılır.

<img src="https://user-images.githubusercontent.com/38913989/200574061-2309ae30-cc0f-4406-b939-a9b1f5b8c668.PNG" width=30% height=30%> 
<img src="https://user-images.githubusercontent.com/38913989/200574103-963387c0-01a4-4266-b448-4fb3dee3a5da.PNG" width=30% height=30%>

7) <b>/order/getOrdersByDate/{startDate}/{endDate}</b> endpointine istek atılarak belirli iki tarih arasındaki siparişler görüntülenebilir.
![İki tarih arası](https://user-images.githubusercontent.com/38913989/200535976-742e0d5d-3aaa-4a21-9663-43f2fbcd0361.PNG)

8) Belirli bir müşterinin aylara göre istatistikleri sorgulanır. <b>/statistics/getMonthlyStatistic/{müşteri numarası}</b> endpointine istek atılır.

![montly istatistik](https://user-images.githubusercontent.com/38913989/200536365-8185daea-66bb-4fda-997a-cd510803fe97.PNG)

## Error Response

Kullanıcı validasyonlara takıldığı zamanlarda ona custom hatalar gösterilir. Birkaç örnek:  

- Aynı email ile yeni müşteri oluşturulmaya çalışıldığında:
![Aynı email](https://user-images.githubusercontent.com/38913989/200578289-b3cf7e6d-1fde-4a41-b2e1-7d83b1d923bd.PNG)   

- Geçersiz kitap ücreti girildiğinde:
![invalid price](https://user-images.githubusercontent.com/38913989/200579258-87ee874d-197b-4d0a-8c40-0b47bd627de2.PNG)   

- Geçersiz kitap stoğu girildiğinde:
![invalid stock](https://user-images.githubusercontent.com/38913989/200579310-8b65b356-717c-4489-82c4-8d3552f0fc63.PNG)

## Swagger

http://localhost:8080/swagger-ui.html

![Swagger](https://user-images.githubusercontent.com/38913989/200543416-5c6ab9f1-6f4d-4112-b18e-ac5626b40013.PNG)



