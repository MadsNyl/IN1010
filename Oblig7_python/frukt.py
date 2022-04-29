from settings import *

class Frukt:
    def __init__(self, vindu, x, y):
        self.vindu = vindu
        self.x = x
        self.y = y

    # tegn frukten
    def tegn(self):
        pg.draw.rect(self.vindu, ROD, (RUTESTORRELSE * self.x, RUTESTORRELSE * self.y, RUTESTORRELSE, RUTESTORRELSE))

