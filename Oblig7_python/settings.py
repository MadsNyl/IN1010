import pygame as pg

# vindu
BREDDE = 500
HOYDE = 500
RUTESTORRELSE = 25
RADER = HOYDE // RUTESTORRELSE
KOLONNER = BREDDE // RUTESTORRELSE

# farger
SVART = (0, 0, 0)
HVIT = (255, 255, 255)
GUL = (255, 255, 0)
ROD = (255, 0, 0)

# klokke
FPS = 5
klokke = pg.time.Clock()