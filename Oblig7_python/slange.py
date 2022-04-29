from settings import *

class Slange:
    def __init__(self, vindu, x, y):
        self.vindu = vindu
        self.x = x
        self.y = y
        self.vel_x = 0
        self.vel_y = 0
        self.hale = []

    # tegner slangehode paa spillbrettet
    def tegn(self):
        pg.draw.rect(self.vindu, GUL, (RUTESTORRELSE * self.x, RUTESTORRELSE * self.y, RUTESTORRELSE, RUTESTORRELSE))
    
    def tegn_hale(self):
        for hale in self.hale:
            hale.tegn()

    def oppdater_hale(self):
        for i, hale in enumerate(self.hale):
            if (i != 0):
                hale.x = self.hale[i - 1].x
                hale.y = self.hale[i - 1].y


    # ser etter input fra tastatur
    def beveg(self):
        keys = pg.key.get_pressed()

        if keys[pg.K_LEFT]:
            self.vel_x = -1
            self.vel_y = 0
        elif keys[pg.K_RIGHT]:
            self.vel_x = 1
            self.vel_y = 0
        elif keys[pg.K_UP]:
            self.vel_y = -1
            self.vel_x = 0
        elif keys[pg.K_DOWN]:
            self.vel_y = 1
            self.vel_x = 0
         
    # oppdaterer posisjon til slangehode
    def oppdater(self):
        self.kollisjon()
        self.x += self.vel_x
        self.y += self.vel_y
    
    # sjekk for kollisjon med sidene paa spillbrettet
    def kollisjon(self):
        if (self.x < 0):
            pg.quit()
        elif (self.x >= RADER):
            pg.quit()
        elif (self.y < 0):
            pg.quit()
        elif (self.y >= KOLONNER):
            pg.quit()

    # legg til element i hale
    def legg_til_hale(self, hale):
        self.hale.append(hale)

    def getPos(self):
        print(self.x, self.y)
