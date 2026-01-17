Automatizirano testiranje: Hervis.hr (Selenium & TestNG)

Ovaj projekt predstavlja napredni sustav za automatizirano testiranje web trgovine Hervis.hr. Projekt je izrađen prema principima kvalitete koda i modernim praksama automatizacije.

U projekt su integrirane sljedeće tehnike:

  Page Object Model (POM): Projekt je strukturiran tako da su lokatori i akcije odvojeni od samih testova (mape pages i tests), što omogućuje lakše održavanje koda.
  OOP (Object-Oriented Programming): Korišteno je nasljeđivanje. HervisHomePage nasljeđuje BasePage, čime se izbjegava dupliciranje koda i omogućuje ponovna uporaba metoda.
  Wait naredbe (Explicit Waits): Umjesto nestabilnih fiksnih pauza, projekt koristi WebDriverWait i ExpectedConditions za sinkronizaciju s elementima, što testove čini robusnijima.
  .gitignore: Projekt sadrži optimiziranu datoteku koja sprječava slanje nepotrebnih (target/, .idea/) datoteka na repozitorij.
  U okviru @BeforeMethod anotacije implementirana je logika koja omogućuje pokretanje testova na različitim preglednicima. Sustav dinamički inicijalizira odgovarajući WebDriver ovisno o postavljenoj varijabli:

    Firefox: Koristi se kao primarni preglednik za testiranje (GeckoEngine).

    Chrome: Podržan putem ChromeDriver-a (Blink engine).

    Edge: Podržan putem EdgeDriver-a (Chromium engine).
  Reporting: U pom.xml je integriran maven-surefire-report-plugin koji omogućuje automatsko generiranje detaljnih izvještaja o prolaznosti testova.
  README.md: Dokumentacija projekta s uputama i opisima.

  Tehnologije

    Java 25 (Programming Language)

    Selenium WebDriver 4.27.0 (Browser Automation)

    TestNG 7.11.0 (Testing Framework)

    Maven (Dependency & Build Management)

Struktura projekta i POM

Testovi su organizirani kroz Page Object Model kako bi se postigla visoka razina apstrakcije:

    src/main/java/pages/BasePage.java: Sadrži zajedničke metode (poput siguranKlik i scrollDown) i inicijalizaciju WebDriverWait-a. Ovo je bazna klasa koju ostale stranice nasljeđuju (OOP princip).

    src/main/java/pages/HervisHomePage.java: Sadrži specifične lokatore i metode za interakciju s Hervis web stranicom.

    src/test/java/HervisTest.java: Sadrži same testne slučajeve i validacije (Assert-ove).

Popis i opis testova

    Provjera vidljivosti logotipa (test01_LogoVisible): Provjerava ispravno učitavanje vizualnog identiteta stranice traženjem logotipa putem CSS selektora.

    Pretraga proizvoda (test02_PretragaTenisice): Simulira unos pojma "tenisice", pritisak tipke Enter te provjerava sadrži li rezultirajući URL traženi pojam.

    Navigacija u košaricu (test03_KosaricaStranica): Provjerava pristupačnost košarice klikom na ikonu. Sadrži "fallback" mehanizam koji osigurava pristup čak i ako je element zaklonjen.

    Pristup akcijskim ponudama (test04_Akcije): Testira navigaciju do Sale/Outlet kategorija pomoću sigurne metode klika.

    Lokator trgovina (test05_Trgovine): Provjerava rad stranice za pronalaženje fizičkih poslovnica.

    Dostupnost login stranice (test06_LoginStranica): Izravno provjerava je li sustav za autentifikaciju korisnika dostupan.

    Prisutnost Newslettera (test07_NewsletterFooter): Provjerava postojanje sekcije za pretplatu u podnožju stranice nakon skrolanja.

    Poveznice na društvene mreže (test08_DrustveneMreze): Validira postojanje linkova za Facebook i Instagram, čime se potvrđuje marketinška integracija.

Upute za uporabu
Preduvjeti

    Instaliran Java JDK 25 (ili noviji)

    Instaliran Maven

    Instaliran Firefox (zadani preglednik u kodu) ili Chrome/Edge

Pokretanje projekta

    Klonirajte repozitorij: git clone https://github.com/eivelic/mttps-hervis-test.git
    Pozicionirajte se u mapu projekta: cd mttps-hervis-test
    Pokrenite testove putem Mavena: mvn test
    Generirajte izvještaj: mvn surefire-report:report (Izvještaj će se nalaziti u: target/site/surefire-report.html)
