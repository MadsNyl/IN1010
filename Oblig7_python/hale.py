from settings import *

class Hale:
    def __init__(self, vindu, x, y):
        self.vindu = vindu
        self.x = x
        self.y = y
    
    # tegn hale_del
    def tegn(self):
        pg.draw.rect(self.vindu, HVIT, (RUTESTORRELSE * self.x, RUTESTORRELSE * self.y, RUTESTORRELSE, RUTESTORRELSE))