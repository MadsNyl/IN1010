import pygame as pg
from settings import *

class Rutenett:
    def __init__(self, rader, kolonner, bredde, hoyde, vindu):
        self.rader = rader
        self.kolonner = kolonner
        self.bredde = bredde
        self.hoyde = hoyde
        self.vindu = vindu
    
    # tegner inn rutenett paa spillbrettet
    def tegn(self):
        for x in range(RADER):
            pg.draw.line(self.vindu, HVIT, (0, RUTESTORRELSE * x), (BREDDE, RUTESTORRELSE * x))
        
        for y in range(KOLONNER):
            pg.draw.line(self.vindu, HVIT, (RUTESTORRELSE * y, 0), (RUTESTORRELSE * y, HOYDE))

