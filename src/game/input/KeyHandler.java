package game.input;

import game.entity.Medic;
import game.entity.Scientist;
import game.entity.Soldier;
import game.entity.Survivor;
import game.graphics.GamePanel;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.security.Key;

public class KeyHandler implements KeyListener {

    public boolean upPressed, downPressed, leftPressed, rightPressed, ePressed;

    private GamePanel gp;

    public KeyHandler(GamePanel gp) {
        this.gp = gp;
    }

    @Override
    public void keyTyped(KeyEvent keyEvent) {
    }

    @Override
    public void keyPressed(KeyEvent keyEvent) {
        int code = keyEvent.getKeyCode();

        // title state
        if (gp.gameState == gp.titleState) {
            titleState(code);
        } else if (gp.gameState == gp.playState) {
            playState(code);
        } else if (gp.gameState == gp.pauseState) {
            pauseState(code);
        } else if (gp.gameState == gp.dialogueState) {
            dialogueState(code);
        } else if (gp.gameState == gp.combatState) {
            combatState(code);
        } else if (gp.gameState == gp.inventoryState) {
            inventoryState(code);
        } else if (gp.gameState == gp.gameOverState) {
            gameOverState(code);
        } else if (gp.gameState == gp.lootState) {
            lootState(code);
        }
    }

    @Override
    public void keyReleased(KeyEvent keyEvent) {
        int code = keyEvent.getKeyCode();

        if (code == KeyEvent.VK_W) {
            upPressed = false;
        }
        if (code == KeyEvent.VK_S) {
            downPressed = false;
        }
        if (code == KeyEvent.VK_A) {
            leftPressed = false;
        }
        if (code == KeyEvent.VK_D) {
            rightPressed = false;
        }
        if (code == KeyEvent.VK_E) {
            ePressed = false;
        }
    }

    private void titleState(int code) {
        if (gp.getUi().getTitleScreenState() == 0) {
            if (code == KeyEvent.VK_W) {
                gp.getUi().setCommandNum(((gp.getUi().getCommandNum()) - 1), 2);
            }
            if (code == KeyEvent.VK_S) {
                gp.getUi().setCommandNum(((gp.getUi().getCommandNum()) + 1), 2);
            }
            if (code == KeyEvent.VK_ENTER) {
                if (gp.getUi().getCommandNum() == 0) {
                    gp.getUi().setTitleScreenState(1);
                }
                if (gp.getUi().getCommandNum() == 1) {
                    // add later
                }
                if (gp.getUi().getCommandNum() == 2) {
                    System.exit(0);
                }
            }
        } else if (gp.getUi().getTitleScreenState() == 1) {
            if (code == KeyEvent.VK_W) {
                gp.getUi().setCommandNum(((gp.getUi().getCommandNum()) - 1), 4);
            }
            if (code == KeyEvent.VK_S) {
                gp.getUi().setCommandNum(((gp.getUi().getCommandNum()) + 1), 4);
            }
            if (code == KeyEvent.VK_ENTER) {
                if (gp.getUi().getCommandNum() == 0) {
                    gp.setPlayer(new Survivor("Player", gp, this));
                    gp.getUi().setCommandNum(0, 2);
                    gp.startGame();
                }
                if (gp.getUi().getCommandNum() == 1) {
                    gp.setPlayer(new Medic("Player", gp, this));
                    gp.getUi().setCommandNum(0, 2);
                    gp.startGame();
                }
                if (gp.getUi().getCommandNum() == 2) {
                    gp.setPlayer(new Scientist("Player", gp, this));
                    gp.getUi().setCommandNum(0, 2);
                    gp.startGame();
                }
                if (gp.getUi().getCommandNum() == 3) {
                    gp.setPlayer(new Soldier("Player", gp, this));
                    gp.getUi().setCommandNum(0, 2);
                    gp.startGame();
                }
                if (gp.getUi().getCommandNum() == 4) {
                    gp.getUi().setTitleScreenState(0);
                    gp.getUi().setCommandNum(0, 2);
                }
            }
        }
    }

    private void playState(int code) {
        if (code == KeyEvent.VK_W) {
            upPressed = true;
        }
        if (code == KeyEvent.VK_S) {
            downPressed = true;
        }
        if (code == KeyEvent.VK_A) {
            leftPressed = true;
        }
        if (code == KeyEvent.VK_D) {
            rightPressed = true;
        }
        if (code == KeyEvent.VK_ESCAPE) {
            gp.gameState = gp.pauseState;
        }
        if (code == KeyEvent.VK_E) {
            ePressed = true;
        }
        if (code == KeyEvent.VK_Q) {
            gp.gameState = gp.inventoryState;
        }
    }

    private void pauseState(int code) {

        if (code == KeyEvent.VK_ESCAPE) {
            gp.gameState = gp.playState;
        }
    }

    private void dialogueState(int code) {
        if (code == KeyEvent.VK_ENTER) {
            gp.gameState = gp.playState;
        }
    }

    private void combatState(int code) {
        int combatScreenState = gp.getCombatHandler().getCombatScreenState();

        if (combatScreenState == 0) {
            // options state
            if (code == KeyEvent.VK_W) {
                gp.getUi().setCommandNum(((gp.getUi().getCommandNum()) - 1), 2);
            }
            if (code == KeyEvent.VK_S) {
                gp.getUi().setCommandNum(((gp.getUi().getCommandNum()) + 1), 2);
            }
            if (code == KeyEvent.VK_ENTER) {
                if (gp.getUi().getCommandNum() == 0) {
                    /* ------------ PLAYER CHOSES FIGHT ---------------- */

                    gp.getCombatHandler().setCombatScreenState(1);
                }
                if (gp.getUi().getCommandNum() == 1) {
                    /* ------------ PLAYER CHOSES CHANGE ITEM ---------------- */

                    gp.getCombatHandler().setCombatScreenState(10);
                }
                if (gp.getUi().getCommandNum() == 2) {
                    /* ------------ PLAYER CHOSES RUN ---------------- */

                    gp.getCombatHandler().run();
                }
            }
        } else if (combatScreenState == 1) {
            // chose to shoot
            if (code == KeyEvent.VK_W) {
                gp.getUi().setCommandNum((gp.getUi().getCommandNum() - 1), 1);
            }
            if (code == KeyEvent.VK_S) {
                gp.getUi().setCommandNum((gp.getUi().getCommandNum() + 1), 1);
            }
            if (code == KeyEvent.VK_ENTER) {
                if (gp.getUi().getCommandNum() == 0) {
                    // player shoots
                    gp.getCombatHandler().playerAttack();
                }
                if (gp.getUi().getCommandNum() == 1) {
                    // player returns to intial screen
                    gp.getCombatHandler().setCombatScreenState(0);
                }
            }
        } else if (combatScreenState == 2) {
            // shows the message after the player attacks or changes item, when the player presses enter the target can attack
            if (code == KeyEvent.VK_ENTER) {
                gp.getCombatHandler().targetAttack();
            }
        } else if (combatScreenState == 3) {
            // shows the message after the target tried to attack, then it returns to the initial state
            if (code == KeyEvent.VK_ENTER) {
                gp.getCombatHandler().setCombatScreenState(0);
            }
        } else if (combatScreenState == 4) {
            // end state
            if (code == KeyEvent.VK_ENTER) {
                // the way that this is implemented, the enemy will be removed whether the player or the enemy has died
                // it doesnt really matter cuz the game will end if the player dies, so its ok if the enemy is removed
                gp.getCombatHandler().removeEnemy();
                gp.getCombatHandler().setCharsAtOrgPos();
                gp.gameState = gp.playState;
            }
        } else if (combatScreenState == 10) {
            /* ---------------- INVENTORY/ITEMS OPTION ------------------- */

            if (gp.getUi().getInventoryScreenState() == 0) {
                if (code == KeyEvent.VK_Q) {
                    gp.getCombatHandler().setCombatScreenState(0);
                }
                if (code == KeyEvent.VK_W) {
                    if (gp.getUi().getSlotRow() != 0) {
                        gp.getUi().setSlotRow(gp.getUi().getSlotRow() - 1);
                    }
                }
                if (code == KeyEvent.VK_S) {
                    if (gp.getUi().getSlotRow() != 3) {
                        gp.getUi().setSlotRow(gp.getUi().getSlotRow() + 1);
                    }
                }
                if (code == KeyEvent.VK_D) {
                    if (gp.getUi().getSlotCol() != 4) {
                        gp.getUi().setSlotCol(gp.getUi().getSlotCol() + 1);
                    }
                }
                if (code == KeyEvent.VK_A) {
                    if (gp.getUi().getSlotCol() != 0) {
                        gp.getUi().setSlotCol(gp.getUi().getSlotCol() - 1);
                    }
                }
                if (code == KeyEvent.VK_ENTER) {
                    if (gp.getUi().isSlotNotEmpty()) {
                        gp.getUi().setInventoryScreenState(1);
                    }
                }
            } else if (gp.getUi().getInventoryScreenState() == 2) {
                if (code == KeyEvent.VK_W) {
                    gp.getUi().setCommandNum(((gp.getUi().getCommandNum()) - 1), 2);
                }
                if (code == KeyEvent.VK_S) {
                    gp.getUi().setCommandNum(((gp.getUi().getCommandNum()) + 1), 2);
                }
                if (code == KeyEvent.VK_ENTER) {
                    if (gp.getUi().getCommandNum() == 0) {
                        gp.getPlayer().selectItem();
                        gp.getUi().setInventoryScreenState(0);
                    }
                    if (gp.getUi().getCommandNum() == 2) {
                        gp.getUi().setInventoryScreenState(0);
                    }
                    gp.getUi().setCommandNum(0, 2);
                }
            } else if (gp.getUi().getInventoryScreenState() == 3) {
                if (code == KeyEvent.VK_W) {
                    gp.getUi().setCommandNum(((gp.getUi().getCommandNum()) - 1), 1);
                }
                if (code == KeyEvent.VK_S) {
                    gp.getUi().setCommandNum(((gp.getUi().getCommandNum()) + 1), 1);
                }
                if (code == KeyEvent.VK_ENTER) {
                    if (gp.getUi().getCommandNum() == 1) {
                        gp.getPlayer().selectItem();
                        gp.getUi().setInventoryScreenState(0);
                    }
                    gp.getUi().setCommandNum(0, 1);
                }
            }
        }
    }

    private void inventoryState(int code) {
        if (gp.getUi().getInventoryScreenState() == 0) {
            if (code == KeyEvent.VK_Q) {
                gp.gameState = gp.playState;
            }
            if (code == KeyEvent.VK_W) {
                if (gp.getUi().getSlotRow() != 0) {
                    gp.getUi().setSlotRow(gp.getUi().getSlotRow() - 1);
                }
            }
            if (code == KeyEvent.VK_S) {
                if (gp.getUi().getSlotRow() != 3) {
                    gp.getUi().setSlotRow(gp.getUi().getSlotRow() + 1);
                }
            }
            if (code == KeyEvent.VK_D) {
                if (gp.getUi().getSlotCol() != 4) {
                    gp.getUi().setSlotCol(gp.getUi().getSlotCol() + 1);
                }
            }
            if (code == KeyEvent.VK_A) {
                if (gp.getUi().getSlotCol() != 0) {
                    gp.getUi().setSlotCol(gp.getUi().getSlotCol() - 1);
                }
            }
            if (code == KeyEvent.VK_ENTER) {
                if (gp.getUi().isSlotNotEmpty()) {
                    gp.getUi().setInventoryScreenState(1);
                }
            }
        } else if (gp.getUi().getInventoryScreenState() == 2) {
            if (code == KeyEvent.VK_W) {
                gp.getUi().setCommandNum(((gp.getUi().getCommandNum()) - 1), 2);
            }
            if (code == KeyEvent.VK_S) {
                gp.getUi().setCommandNum(((gp.getUi().getCommandNum()) + 1), 2);
            }
            if (code == KeyEvent.VK_ENTER) {
                if (gp.getUi().getCommandNum() == 0) {
                    gp.getPlayer().selectItem();
                    gp.getUi().setInventoryScreenState(0);
                }
                if (gp.getUi().getCommandNum() == 1) {
                    gp.getPlayer().removeItem();
                    gp.getUi().setInventoryScreenState(0);
                }
                if (gp.getUi().getCommandNum() == 2) {
                    gp.getUi().setInventoryScreenState(0);
                }
                gp.getUi().setCommandNum(0, 2);
            }
        } else if (gp.getUi().getInventoryScreenState() == 3) {
            if (code == KeyEvent.VK_W) {
                gp.getUi().setCommandNum(((gp.getUi().getCommandNum()) - 1), 1);
            }
            if (code == KeyEvent.VK_S) {
                gp.getUi().setCommandNum(((gp.getUi().getCommandNum()) + 1), 1);
            }
            if (code == KeyEvent.VK_ENTER) {
                if (gp.getUi().getCommandNum() == 0) {
                    gp.getPlayer().removeItem();
                    gp.getUi().setInventoryScreenState(0);
                }
                if (gp.getUi().getCommandNum() == 1) {
                    gp.getUi().setInventoryScreenState(0);
                }
                gp.getUi().setCommandNum(0, 1);
            }
        }
    }

    private void lootState(int code) {
        if (gp.getUi().getInventoryScreenState() == 0) {
            if (code == KeyEvent.VK_Q) {
                gp.gameState = gp.playState;
            }
            if (code == KeyEvent.VK_W) {
                if (gp.getUi().getSlotRow() != 0) {
                    gp.getUi().setSlotRow(gp.getUi().getSlotRow() - 1);
                }
            }
            if (code == KeyEvent.VK_S) {
                if (gp.getUi().getSlotRow() != 3) {
                    gp.getUi().setSlotRow(gp.getUi().getSlotRow() + 1);
                }
            }
            if (code == KeyEvent.VK_D) {
                if (gp.getUi().getSlotCol() != 4) {
                    gp.getUi().setSlotCol(gp.getUi().getSlotCol() + 1);
                }
            }
            if (code == KeyEvent.VK_A) {
                if (gp.getUi().getSlotCol() != 0) {
                    gp.getUi().setSlotCol(gp.getUi().getSlotCol() - 1);
                }
            }
            if (code == KeyEvent.VK_ENTER) {
                if (gp.getUi().isSlotNotEmpty()) {
                    gp.getUi().setInventoryScreenState(1);
                }
            }
        } else if (gp.getUi().getInventoryScreenState() == 1) {
            if (code == KeyEvent.VK_W) {
                gp.getUi().setCommandNum(((gp.getUi().getCommandNum()) - 1), 2);
            }
            if (code == KeyEvent.VK_S) {
                gp.getUi().setCommandNum(((gp.getUi().getCommandNum()) + 1), 2);
            }
            if (code == KeyEvent.VK_ENTER) {
                if (gp.getUi().getCommandNum() == 0) {
                    gp.getPlayer().selectLoot();
                    gp.getUi().setInventoryScreenState(0);
                }
                if (gp.getUi().getCommandNum() == 1) {
                    gp.getUi().setInventoryScreenState(0);
                }
                gp.getUi().setCommandNum(0, 2);
            }
        }
    }

    private void gameOverState(int code) {
        if (code == KeyEvent.VK_W) {
            gp.getUi().setCommandNum(((gp.getUi().getCommandNum()) - 1), 1);
        }
        if (code == KeyEvent.VK_S) {
            gp.getUi().setCommandNum(((gp.getUi().getCommandNum()) + 1), 1);
        }
        if (code == KeyEvent.VK_ENTER) {
            if (gp.getUi().getCommandNum() == 0) {
                gp.gameState = gp.titleState;
                gp.getEnvM().setInitialState();
                gp.getClock().setInitialState();
            }
            if (gp.getUi().getCommandNum() == 1) {
                System.exit(0);
            }
        }
    }
}
