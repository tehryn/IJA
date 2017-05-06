Projekt do předmětu IJA v akademickém roce 2016/2017.

Autoři projektu: Jiří Matějka a Miroslava Míšová.
Loginy autorů: xmatej52, xmisov00.


Popis hry:
Po spuštění aplikace pomocí příkazu "ant run" nebo spuštěním z
adresáře projektu (složka xmatej52) se otevře prázdné zelené okno.
V menu je možnost vybrat, kterou hru chcete začít hrát, pokud hrajete pouze
jednu hru, doporučuje se položka menu game1.

New game:
Značí vytvoření nové hry,

Open:
Načtení hry ze souboru,

Save:
Uložení hry

Leave game:
Opuštění hry

Undo:
navrácení o jeden tah zpět (o více při použití vícekrát)

Hint:
Zoprazí možný tah. Zobrazené tahy jsou pouze z pracovních balíčků
do barevných, mezi pracovními balíčky a z viditelného balíčku do
barevných a pracovních. Tahy z Barevných do pracovních, mezi barevnými balíčky
a tah kliknutí na zásobních skrytých karet se nezobrazují.

Exit program:
Ukončí celou aplikaci. Aplikaci je možné ukončit ze všech her.


Klávesové zkratky:
CTRL+N Načtení nové hry
CTRL+S Uloží hru
CTRL+O Načte hru
CTRL+L Opustí hru
CTRL+Q Ukončí aplikaci
CTRL+Z Volání operace undo
CTRL+H Zobrazí nápovědu

Zobrazení karet a hry:
Vzhledem k nutnosti implementace až 4 her v jednom okně jsme zvolili menší karty,
a také menší hrací okno. Nutno dodat, že hrací okno není dostatečně velké, pokud
v pracovním balíčku karet bude více jak 12 karet (v nejhorším případě jich tam
může být až 19), ovšem v případě dvou her nad sebou by muselo být rozlišení
obrazovky obrovské, aby se tam karty vešly. V případě nutnosti (nebo patche)
stačí změnit výchozí velikost karet ve balíčku src.Application a velikost okna,
posuny karet a velikosti všech zásobníků se přizpůsobí nové velikosti. 

