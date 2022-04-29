from random import randint
import sys
from frukt import Frukt
from hale import Hale
from slange import Slange
from settings import *
from rutenett import Rutenett

pg.init()

# setter opp vindu
vindu = pg.display.set_mode([BREDDE, HOYDE])

# setter opp Objekter
rutenett = Rutenett(RADER, KOLONNER, BREDDE, HOYDE, vindu)
slange = Slange(vindu, 10, 10)
frukt = Frukt(vindu, 5, 5)
slange.legg_til_hale(slange)

# spill loop
kjor = True
while kjor:
    # tegner inn paa spillbrettet
    vindu.fill(SVART)
    frukt.tegn()
    rutenett.tegn()
    slange.tegn()

    # ser etter trykk paa exit-knapp
    for event in pg.event.get():
        if event.type == pg.QUIT:
            kjor = False
            sys.exit()

    if (slange.x == frukt.x and slange.y == frukt.y):
        frukt.x = randint(0, RADER - 1)
        frukt.y = randint(0, KOLONNER - 1)
        hale_del = Hale(vindu, slange.x, slange.y)
        slange.legg_til_hale(hale_del)
    
    slange.beveg()
    
    # oppdaterer elementer paa brettet
    slange.oppdater()
    slange.oppdater_hale()
    slange.tegn_hale()

    pg.display.update()

    klokke.tick(FPS)

pg.quit()