# ZavrsniProjekat

Na primeru aplikacije demo.yo-meals.com demonstrirati proces testiranja softvera primenom Selenium, TestNG i ApachePOI biblioteka, sa dostupnom kompletnom implementacijom na github nalogu.

Saveti i smernice
●	Sve linkove koje koristite u testovima moraju da budu u formi baseUrl + ruta. Na primer link za login stranu bi izgledao baseUrl + "/guest-user/login-form"
●	Za prijavu koristite atribute email i password Basic Test klase
●	Za verifikovanje poruka koristite contains metodu.
●	Ako imate verifikovanje poruka koje su u više redova, na primer dva, u toj situaciji verifikujte prvo prvi red poruke pa zatim i drugi red.
●	Na git komitujte manje celine
●	Kod i poruke komitova OBAVEZNO na engleskom.
●	Podesite .gitignore fajl koji je prilagođen za organizaciju projekta koja je navedena u daljem tekstu.
●	Za svaku proveru u okviru testova obavezno postavite odgovarajuće poruke u slučaju neočekivanog ishoda. Npr: [ERROR] Unexpected logout message
●	Screenshot-ove koje kreirate u AfterMethod metodi nazivajte prema trenutnom vremenu.Npr: 2021-19-02-21-20-22.png
●	Konfigurišite timeout-e za implicitno čekanje i učitavanje strane.
●	Promenljive nazivajte tako da imaju neko logično značenje. Izbegavajte jednoslovne promenljive kao što su: x, y, e, b, d,...
●	Ukoliko vam u projektu zatreba provera da li neki element postoji na stranici, iskoristite rešenje sa listom.
●	Postavite prioritete testova

Implementacija
Organizovati projekat tako da ima sledeću strukturu:
●	paket tests
●	paket pages 
●	direktorijum lib - za sve biblioteke koje se koriste u okviru projekta
●	direktorijum driver-lib - za drajvere
●	direktorijum screenshots - za smeštanje screenshot slika
●	direktorijum img- za smeštanje profilnih slika
●	direktorijum data - za fajlove u kojima čuvamo podatke

Od page klasa je potrebno implementirati:
Basic Page:
●	apstraktna klasa koja sadrži sve zajedničke funkcionalnosti page klasa
●	sve ostale page klase nasleđuju ovu klasu

Location Popup Page:
●	get metoda za element koji prikazuje lokaciju u hederu 
●	get metodu za close element
●	get metode potrebne za implementaciju metode u kojoj se postavlja lokacija
○	getKeyword()
■	//*[@id='locality_keyword']
○	getLocationItem(String locationName)
■	//li/a[contains(text(), '" + locationName + "')]/..
■	i nije greska na kraju postoje dve tacke!
○	getLocationInput()
■	//*[@id='location_id']
○	getSubmit()
■	//*[@name='btn_submit']
●	metodu koja otvara iskačući dijalog
○	klikom na lokaciju iz hedera
●	metodu koja postavlja lokaciju - naziv lokacije (locationName) se prosleđuje kao parametar metode
○	metoda prvo klikne na element keyword element
○	čita vrednost data-value atributa location item elementa
○	postavlja lokaciju izvršavajući JavaScript kod
○	Skripta: arguments[0].value=arguments[1]
■	prvi argument skripte je location input
■	drugi argument skripte je vrednost pročitanog atributa iz drugog koraka.
○	Klikće na submit element preko skripte arguments[0].click();
●	metodu koja zatvara iskačući dijalog, klikom na X dugme

Login Page:
●	get metode za sve potrebne elemente
●	metodu koja prijavljuje korisnika na sistem - kao parametri se prosleđuju imejl i lozinka

Notification Sistem Page:
●	get metodu za element koji prikazuje poruku
○	//*[contains(@class, 'alert--success') or contains(@class, 'alert--danger')][contains(@style,'display: block')]
●	metodu koja vraća poruku koja se nalazi u obaveštenju
●	metodu koja čeka da obaveštenje nestane
○	čeka se da element //*[contains(@class, 'system_message')]
○	za atribut style dobije vrednost  "display: none;"
	
Profile Page:
●	get metode za sve potrebne elemente
○	sve elemente za izmenu osnovnih informacija kao i 
○	sve elemente potrebne za rad sa profilnom slikom
○	da bi se na stranici pojavio element input type="file" potrebno je da se prvo izvrši JavaScript kod koji vrši akciju klik na Upload dugme 
○	Skripta: arguments[0].click();
●	metodu koja otprema profilnu sliku - kao parametar se prosleđuje putanja do fajla, tj. u ovom slučaju do slike
●	metodu koja briše profilnu sliku
○	klikom na Remove dugme 
○	Preporuka: izvršite JavaScript kod arguments[0].click(); nad tim dugmetom
●	metodu koja menja sve osnovne informacije korisnika - kao parametri se prosleđuju sve potrebne informacije

Auth Page:
●	get metode za sve potrebne elemente sa stranice   
●	metodu koja odjavljuje korisnika sa sistema

Meal Page:
●	get metode za sve potrebne elemente
●	metodu koja dodaje jelo u korpu - kao parametar se prosleđuje količina
●	metodu koja jelo dodaje u omiljena jela, klikom na dugme Favorite 

Cart Summary Page:
●	get metodu za Clear All dugme
●	metodu koja briše sve stavke iz korpe klikom na Clear All dugme

Search Result Page:
●	get metodu za sve rezultate pretrage //*[@class='product-name']/a
●	metodu koja vraća nazive svih jela dobijenih pretragom
●	metodu koja vraća broj rezultata pretrage

Od test klasa je potrebno implementirati:
Basic Test:
●	apstraktna klasa koja sadrži sve zajedničke funkcionalnosti za sve test klase
●	od dodatnih atributa ima:
○	baseUrl 
○	imejl i lozinku demo korisnika customer@dummyid.com/12345678a
●	BeforeClass metoda koja konfiguriše Selenium drajver
●	AfterMethod metoda koja u slučaju pada testa kreira screenshot stranice i te slike čuva u okviru screenshots direktorijuma. Nevezano za ishod testa metoda uvek briše sve kolačiće.
●	AfterClass metoda koja zatvara sesiju drajvera
●	sve ostale test klase nasleđuju ovu klasu

Profile Test klasa:
U okviru edit profile testa potrebno je izvršiti sledeće korake:
●	učitajte stranicu http://demo.yo-meals.com/guest-user/login-form
●	ugasite lokacioni iskačući dijalog
●	prijavite se na aplikaciju preko demo naloga
●	verifikujte da je prikazana poruka sa tekstom "Login Successfull"
●	učitajte stranicu http://demo.yo-meals.com/member/profile
●	zamenite sve osnovne informacije korisnika
●	verifikujte da je prikazana poruka sa tekstom "Setup Successful"
●	odjavite se sa sajta
●	verifikujte da je prikazana poruka sa tekstom "Logout Successfull!"

U okviru change profile image testa potrebno je izvršiti sledeće korake:
●	učitajte stranicu http://demo.yo-meals.com/guest-user/login-form
●	ugasite lokacioni iskačući dijalog
●	prijavite se na aplikaciju preko demo naloga
●	verifikujte da je prikazana poruka sa tekstom "Login Successfull"
●	učitajte stranicu http://demo.yo-meals.com/member/profile
●	otpremite profilnu sliku
○	sliku iz images foldera
○	s obzirom na to da se za otpremanje šalje apsolutna putanja do slike, a mi koristimo relativnu, moramo da pribavimo putanju na sledeći način
○	String imgPath = new File("imagеs/slika.png").getCanonicalPath();
○	Koristan link
●	verifikujte da je prikazana poruka sa tekstom "Profile Image Uploaded Successfully"
●	sačekajte da nestane obaveštenje
●	obrišite profilnu sliku
●	verifikujte da je prikazana poruka sa tekstom "Profile Image Deleted Successfully"
●	sačekajte da nestane obaveštenje
●	odjavite se sa sajta
●	verifikujte da je prikazana poruka sa tekstom "Logout Successfull!"

Meal Item Test:
U okviru add meal to cart testa potrebno je izvršiti sledeće korake:
●	učitajte stranicu http://demo.yo-meals.com/meal/lobster-shrimp-chicken-quesadilla-combo
●	ugasite lokacioni iskačući dijalog
●	dodajte jelo u korpu, količina je proizvoljna
●	verifikujte da je prikazana poruka sa tekstom 
"The Following Errors Occurred:
Please Select Location"
●	sačekajte da obaveštenje nestane
●	postavite lokaciju na "City Center - Albany"
●	dodajte jelo u korpu, količina je proizvoljna
●	verifikujte da je prikazana poruka sa tekstom "Meal Added To Cart"

U okviru add meal to favorite testa potrebno je izvršiti sledeće korake:
●	učitajte stranicu http://demo.yo-meals.com/meal/lobster-shrimp-chicken-quesadilla-combo
●	ugasite lokacioni iskačući dijalog
●	dodajte jelo u omiljena jela
●	verifikujte da je prikazana poruka sa tekstom "Please login first!"
●	učitajte stranicu za prijavu
●	prijavite se na aplikaciju preko demo naloga
●	učitajte stranicu http://demo.yo-meals.com/meal/lobster-shrimp-chicken-quesadilla-combo
●	dodajte jelo u omiljena jela
●	verifikujte da je prikazana poruka sa tekstom "Product has been added to your favorites."

U okviru clear cart testa potrebno je izvršiti sledeće korake:
●	učitajte stranicu http://demo.yo-meals.com/meals
●	postavite lokaciju na "City Center - Albany"
●	čitate podatke iz xlsx fajla > Meals Sheet 
●	u korpu dodajte svaki proizvod sa određenom količinom
●	za svako dodavanje proizvioda verifikujte da je prikazana poruka sa tekstom "Meal Added To Cart"
○	koristite SoftAssert za ovu proveru
●	obrišite sve stavke iz korpe
●	verifikujte da je prikazana poruka sa tekstom "All meals removed from Cart successfully"

Search Test:
U okviru search results testa potrebno je izvršiti sledeće korake:
●	učitajte stranicu http://demo.yo-meals.com/meals
●	postavite lokaciju na "City Center - Albany"
●	čitate podatke iz xlsx fajla > Meals Search Results Sheet
●	izvršite otvaranje svakog linka i postavljanje svake lokacije
●	i za svaku stranicu proverite rezultate pretrage 
●	verifikujte da je broj rezultata na stranici isti kao u fajlu
●	verifikujte da je svaki rezultat na stranici redom prikazan kao u fajlu

Na kraju kreirajte testng.xml fajl u kom ćete pobrojati sve testove na izvršenje.
