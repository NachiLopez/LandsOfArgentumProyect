package tp3.lopez.nazareno.pantallas;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

import tp3.lopez.nazareno.inputoutput.Entradas;
import tp3.lopez.nazareno.interaccion.CreadorMapas;
import tp3.lopez.nazareno.interaccion.WorldContactListener;
import tp3.lopez.nazareno.items.Item;
import tp3.lopez.nazareno.items.ItemConsumible;
import tp3.lopez.nazareno.items.ItemEquipable;
import tp3.lopez.nazareno.jugadores.Jugador;
import tp3.lopez.nazareno.jugadores.Usuario;
import tp3.lopez.nazareno.personajes.Bardo;
import tp3.lopez.nazareno.personajes.Elfo;
import tp3.lopez.nazareno.personajes.Guerrero;
import tp3.lopez.nazareno.personajes.Mago;
import tp3.lopez.nazareno.personajes.Personaje;
import tp3.lopez.nazareno.ui.Boton;
import tp3.lopez.nazareno.ui.BotonAceptar;
import tp3.lopez.nazareno.ui.BotonCancelar;
import tp3.lopez.nazareno.ui.BotonOk;
import tp3.lopez.nazareno.utiles.Config;
import tp3.lopez.nazareno.utiles.Hud;
import tp3.lopez.nazareno.utiles.Imagen;
import tp3.lopez.nazareno.utiles.ItemsRandom;
import tp3.lopez.nazareno.utiles.Render;
import tp3.lopez.nazareno.utiles.Texto;
import tp3.lopez.nazareno.utiles.Utiles;

public class Level1 implements Screen {

	SpriteBatch b = Render.batch;
	ShapeRenderer sr = Render.sr;
	Entradas entradas = new Entradas();
	private float a = 0, tiempoEspera = 0, ultXCuerpo, ultYCuerpo, ultXMano, ultYMano;

	private OrthographicCamera gameCam;
	private Viewport gamePort;
	private Hud hud;

	private TmxMapLoader mapLoader;
	private TiledMap map;
	private OrthogonalTiledMapRenderer renderer;

	private World world;
	private Box2DDebugRenderer b2dr;

	public static boolean dibujar, cofre, onlyOne = false, pelea, entrarMazmorra, tirarItem, darItem;
	public static int idItem;
	private boolean recuadroItem = false, ePresionada, cantUse, encontrado=false, buscarItemEquipadoCuerpo=false,buscarItemEquipadoMano=false, imagenEquipadoOnlyOne=false, itemDado=false;
	private int indiceItem, buscador = 0;
	private Texto pressKeyA, vidaJugador, magiaJugador, fuerzaJugador, oroJugador, noPuedesUsarItem, itemRandomCofreText;
	private Imagen openChest, imagenEquipadoCuerpo, imagenEquipadoMano, tirarItemCartel;
	private Boton btnAceptar, okBtn, cancelarBtn;
	private Item itemRandomCofre;

	public Jugador usuario;
	public Personaje player;

	public Level1() {
		gameCam = new OrthographicCamera();
		gamePort = new FitViewport(Config.ANCHO / Config.PPM, Config.ALTO / Config.PPM, gameCam);
		gamePort.setScreenWidth(Config.ANCHO);
		gamePort.setScreenHeight(Config.ALTO);

		mapLoader = new TmxMapLoader();
		map = mapLoader.load("mapas/desierto/desierto_mapa.tmx");
		renderer = new OrthogonalTiledMapRenderer(map, Config.PPM);
		gameCam.position.set(gamePort.getScreenWidth() / 2, gamePort.getScreenHeight() / 2, 0);

		world = new World(new Vector2(0, 0), true);
		world.setContactListener(new WorldContactListener());

		int eleccion = EleccionPersonaje.getOpcEleccionPJ();
		if (eleccion == 1) {
			usuario = new Usuario(Esp.nombreDelJugador, new Guerrero(world));
		} else if (eleccion == 2) {
			usuario = new Usuario(Esp.nombreDelJugador, new Elfo(world));
		} else if (eleccion == 3) {
			usuario = new Usuario(Esp.nombreDelJugador, new Mago(world));
		} else if (eleccion == 4) {
			usuario = new Usuario(Esp.nombreDelJugador, new Bardo(world));
		}
		((ItemEquipable) usuario.getInventario()[0]).setEquipado(true);
		((ItemEquipable) usuario.getInventario()[0]).setDesequipado(false);
		player = usuario.getPersonaje();
		b2dr = new Box2DDebugRenderer();

		hud = new Hud(b, usuario);

		new CreadorMapas(world, map);
	}

	@Override
	public void show() {
		b = Render.batch;
		Gdx.input.setInputProcessor(entradas);

		openChest = new Imagen("display/cofreOpen.png");
		openChest.setXY(Config.ANCHO / 2 - openChest.getWidth() / 2, Config.ALTO / 2 - openChest.getHeight() / 2);
		pressKeyA = new Texto("Adventure Script.otf", 16, Color.WHITE, true);

		btnAceptar = new BotonAceptar(entradas, Config.ANCHO / 2 - (int) openChest.getWidth() / 2 + 60,
				Config.ALTO / 2 - (int) openChest.getHeight() / 2 + 30);

		vidaJugador = new Texto("Adventure Script.otf", 19, Color.WHITE, true);
		vidaJugador.setX(1090);
		vidaJugador.setY(187);
		magiaJugador = new Texto("Adventure Script.otf", 19, Color.WHITE, true);
		magiaJugador.setX(1097);
		magiaJugador.setY(152);
		oroJugador = new Texto("Adventure Script.otf", 19, Color.WHITE, true);
		oroJugador.setX(1100);
		oroJugador.setY(235);
		fuerzaJugador = new Texto("Adventure Script.otf", 19, Color.WHITE, true);
		fuerzaJugador.setX(1122);
		fuerzaJugador.setY(120);
		

		noPuedesUsarItem = new Texto("Adventure Script.otf", 19, Color.WHITE, true);

		imagenEquipadoCuerpo = new Imagen("display/imagenEquipado.png");
		
		imagenEquipadoMano = new Imagen("display/imagenEquipado.png");
		
		for (int i = 0; i < usuario.getInventario().length; i++) {
			if(usuario.getInventario()[i] != null) {
				usuario.getInventario()[i].setX(1000);
				usuario.getInventario()[i].setY(550);
			}
		}
		
		tirarItemCartel = new Imagen("display/tirarItemCartel.png");
		tirarItemCartel.setXY(Config.ANCHO / 2 - tirarItemCartel.getWidth() / 2, Config.ALTO / 2 - tirarItemCartel.getHeight() / 2);
		
		okBtn = new BotonOk(entradas);
		okBtn.setXY(Config.ANCHO / 2 - okBtn.getWidth() - 80, Config.ALTO / 2 - okBtn.getHeight() / 2 - 70);
		
		cancelarBtn = new BotonCancelar(entradas);
		cancelarBtn.setXY(Config.ANCHO / 2 + 30, Config.ALTO / 2 - cancelarBtn.getHeight() / 2 - 70);
		
		itemRandomCofreText = new Texto("Adventure Script.otf", 28, Color.WHITE, true);
	}

	public void handleInput(float delta) {
		boolean quieto = true;
		boolean seMueve = false;

		if (entradas.isArriba()) {
			player.b2body.setLinearVelocity(0, 500);
//			System.out.println(player.b2body.getPosition().x); 
			quieto = false;
			seMueve = true;
		} else if (entradas.isAbajo()) {
			player.b2body.setLinearVelocity(0, -500);
			quieto = false;
			seMueve = true;
		} else if (entradas.isDerecha()) {
			player.b2body.setLinearVelocity(500, 0);
			quieto = false;
			seMueve = true;
		} else if (entradas.isIzquierda()) {
			player.b2body.setLinearVelocity(-500, 0);
			quieto = false;
			seMueve = true;
		} else if (quieto) {
			player.b2body.setLinearVelocity(0, 0);
			seMueve = false;
		}

		if (seMueve && pelea) {
			int prob = Utiles.r.nextInt(150) + 1;
			if (prob < 2) {
				entradas.reset();
				player.b2body.setLinearVelocity(0, 0);
				quieto = true;
				seMueve = false;
				Render.app.pantallaDesert = this;
				crearPelea(world, usuario);
			}
		}

//		if(!seMueve && entrarMazmorra) { PARA ENTRAR AL SEGUNDO MAPA (DESPUES LO HAGO)
//			if(!onlyOne2) {
//				Render.app.pantallaMazmorra = new Level2(world);
//			}
//			
//			Render.app.setScreen(Render.app.mazmorra);
//		}
	}

	public void update(float delta) {
		handleInput(delta);

		world.step(1 / 60f, 6, 2);

		gameCam.position.x = player.b2body.getPosition().x + 90;
		gameCam.position.y = player.b2body.getPosition().y + 20;
		gameCam.update();

		player.update(delta);

		renderer.setView(gameCam);
	}

	@Override
	public void render(float delta) {
		update(delta);
		Render.limpiarPantalla(0, 0, 0, a);
		renderer.render();
		b2dr.render(world, gameCam.combined);
		b.setProjectionMatrix(hud.stage.getCamera().combined);
		hud.stage.draw();

		if(!imagenEquipadoOnlyOne) {
			ultXCuerpo = usuario.getInventario()[0].getX();
			ultYCuerpo = usuario.getInventario()[0].getY();
			ultXMano = -100;
			ultYMano = -100;
			imagenEquipadoOnlyOne = true;
		}
		
		reordenarItems(usuario);
		
		sr.begin(ShapeType.Line);
		b.begin();
		statsJugador();
		b.draw(player.getFrameActual(), 535 / Config.PPM, 347.5f / Config.PPM);
		imagenEquipadoCuerpo.setXY(ultXCuerpo, ultYCuerpo);
		imagenEquipadoMano.setXY(ultXMano, ultYMano);
		if (dibujar) {
			pressKeyA.setTexto("Presiona A para abrir el cofre");
			pressKeyA.setX(480);
			pressKeyA.setY(430);
			pressKeyA.dibujar();
			if (entradas.isA()) {
				dibujar = false;
				cofre = true;
			}
		}
		if (cofre) {
			itemRandomCofre = ItemsRandom.getItemRandom();
			if(itemRandomCofre == null) {
				itemRandomCofre = ItemsRandom.getItemRandom();
				if(itemRandomCofre == null) {
					itemRandomCofre = ItemsRandom.getItemRandom();
					if(itemRandomCofre == null) {
						itemRandomCofre = ItemsRandom.getItemRandom();
						if(itemRandomCofre == null) {
							itemRandomCofre = ItemsRandom.getItemRandom();
						}
					}
				}
			}
			itemRandomCofre.getIcono().setSize(48, 48);
			itemRandomCofre.getIcono().setXY(Config.ANCHO / 2 - itemRandomCofre.getWidth() - 123, Config.ALTO - Config.ALTO / 2 - 25);
			itemRandomCofreText.setTexto(itemRandomCofre.getNombre());
			itemRandomCofreText.setX(itemRandomCofre.getIcono().getX() + 100);
			itemRandomCofreText.setY(itemRandomCofre.getIcono().getY() + 40);
			
			openChest.dibujar();
			itemRandomCofre.dibujar();
			itemRandomCofreText.dibujar();
			btnAceptar.dibujar();
			
			if(darItem) {
				for (int i = 0; i < usuario.getInventario().length; i++) {
					if(i<9 && usuario.getInventario()[i] == null && !itemDado) {
						usuario.getInventario()[i] = itemRandomCofre;
						usuario.getInventario()[i].setX(usuario.getInventario()[i - 1].getX());
						usuario.getInventario()[i].setY(usuario.getInventario()[i - 1].getY());
						itemDado = true;
					}
				}
				itemDado = false;
				darItem = false;
			}
		}
		if (Jugador.inventarioIconos) {
			for (int i = 0; i < usuario.getInventario().length; i++) {
				if (usuario.getInventario()[i] != null) {
					if (i > 0 && usuario.getInventario()[i - 1] != null && usuario.getInventario()[i].getX() == usuario.getInventario()[i - 1].getX()) {
						usuario.getInventario()[i].setX(usuario.getInventario()[i - 1].getX() + 40);
						if (i < 9 && usuario.getInventario()[i + 1] != null) {
							usuario.getInventario()[i + 1].setX(usuario.getInventario()[i].getX());
							usuario.getInventario()[i + 1].setY(usuario.getInventario()[i].getY());
						}
					}
					if (usuario.getInventario()[i].getX() >= (1210) && usuario.getInventario()[i].getY() == (510)){
						usuario.getInventario()[i].setX(1000);
						usuario.getInventario()[i].setY(510 - 40);
						if (i<9 && usuario.getInventario()[i + 1] != null) {
							usuario.getInventario()[i + 1].setX(usuario.getInventario()[i].getX());
							usuario.getInventario()[i + 1].setY(usuario.getInventario()[i].getY());
						}
					}
					if (usuario.getInventario()[i].getX() >= (1210)){
						usuario.getInventario()[i].setX(1000);
						usuario.getInventario()[i].setY(550 - 40);
						if (i<9 && usuario.getInventario()[i + 1] != null) {
							usuario.getInventario()[i + 1].setX(usuario.getInventario()[i].getX());
							usuario.getInventario()[i + 1].setY(usuario.getInventario()[i].getY());
						}
					}
					usuario.getInventario()[i].getIcono().setSize(32, 32);
					usuario.getInventario()[i].dibujar(usuario.getInventario()[i].getX(), usuario.getInventario()[i].getY());
				}
			}
		}
		recuadroItem();
		if (recuadroItem) {
			sr.setColor(new Color(1, 0, 0, 1));
			sr.rect(usuario.getInventario()[indiceItem].getIcono().getX(),
					usuario.getInventario()[indiceItem].getIcono().getY(),
					usuario.getInventario()[indiceItem].getIcono().getWidth(),
					usuario.getInventario()[indiceItem].getIcono().getHeight());
			
			if (cantUse) {
				tiempoEspera += 0.1f;
				if (tiempoEspera >= 5f) {
					tiempoEspera = 0;
					cantUse = false;
				}
			}
			
			if (entradas.isE()) {
				if (usuario.getPersonaje().getClase() == usuario.getInventario()[indiceItem].getClase()
						|| usuario.getInventario()[indiceItem].getClase() == ("Todas")) {
					if (!ePresionada) {
						ePresionada = true;
						System.out.println("presiono E, en " + usuario.getInventario()[indiceItem].getNombre());
						if (usuario.getInventario()[indiceItem].getTipo() == "cuerpo") {
							buscarItemEquipadoCuerpo = true;
							encontrado = false;
						} else if (usuario.getInventario()[indiceItem].getTipo() == "mano") {
							buscarItemEquipadoMano = true;
							encontrado = false;
						} else if (usuario.getInventario()[indiceItem].getTipo() == "consumible") {
							((ItemConsumible) usuario.getInventario()[indiceItem]).consumirPocion(usuario);
							if(indiceItem < 9 && usuario.getInventario()[indiceItem + 1] != null) {
								usuario.getInventario()[indiceItem] = usuario.getInventario()[indiceItem + 1];
								usuario.getInventario()[indiceItem + 1] = null;
							} else {
								usuario.getInventario()[indiceItem] = null;
							}
							recuadroItem = false;
						}
					}
				} else {
					cantUse = true;
				}
			}
			if (buscarItemEquipadoCuerpo) {
				while (!encontrado) {
					if (usuario.getInventario()[buscador] != null && !encontrado) {
						System.out.println(buscador);
						if(usuario.getInventario()[buscador].getTipo() == "consumible") {
							System.out.println("El item es consumible " + ((ItemConsumible)usuario.getInventario()[buscador]).getNombre());
							if(usuario.getInventario()[buscador + 1] != null) {
								System.out.println("Sigo buscando");
								buscador++;
							} else {
								((ItemEquipable) usuario.getInventario()[indiceItem]).setEquipado(true);
								((ItemEquipable) usuario.getInventario()[indiceItem]).setDesequipado(false);
								((ItemEquipable) usuario.getInventario()[indiceItem]).aplicarEfecto(usuario);
								imagenEquipadoCuerpo.setXY(usuario.getInventario()[indiceItem].getIcono().getX(),
										usuario.getInventario()[indiceItem].getIcono().getY());
								
								ultXCuerpo = imagenEquipadoCuerpo.getX();
								ultYCuerpo = imagenEquipadoCuerpo.getY();
								
								System.out.println("Equipe el item " + ((ItemEquipable) usuario.getInventario()[indiceItem]).getNombre());
								encontrado = true; // PARA FINALIZAR LA BUSQUEDA, YA QUE NO ENCONTRO NINGUN ITEM EQUIPADO.
								buscarItemEquipadoCuerpo = false;
								buscador = 0;
							}
						} else {
							if(((ItemEquipable) usuario.getInventario()[buscador]).isEquipado() && ((ItemEquipable) usuario.getInventario()[buscador]).getTipo() == "cuerpo") {
								System.out.println("buscador " + buscador + " Encontre el item equipado " + ((ItemEquipable) usuario.getInventario()[buscador]).getNombre());
								((ItemEquipable) usuario.getInventario()[buscador]).setEquipado(false);
								((ItemEquipable) usuario.getInventario()[buscador]).setDesequipado(true);
								((ItemEquipable) usuario.getInventario()[buscador]).aplicarEfecto(usuario);
								System.out.println("Desequipé el item en uso " + ((ItemEquipable) usuario.getInventario()[buscador]).getNombre());
								imagenEquipadoCuerpo.setXY(-100, -100);

								ultXCuerpo = imagenEquipadoCuerpo.getX();
								ultYCuerpo = imagenEquipadoCuerpo.getY();
								
								if(buscador != indiceItem) {
									((ItemEquipable) usuario.getInventario()[indiceItem]).setEquipado(true);
									((ItemEquipable) usuario.getInventario()[indiceItem]).setDesequipado(false);
									((ItemEquipable) usuario.getInventario()[indiceItem]).aplicarEfecto(usuario);
									System.out.println("Equipe el item " + ((ItemEquipable) usuario.getInventario()[indiceItem]).getNombre());
									imagenEquipadoCuerpo.setXY(((ItemEquipable) usuario.getInventario()[indiceItem]).getIcono().getX(), ((ItemEquipable) usuario.getInventario()[indiceItem]).getIcono().getY());

									ultXCuerpo = imagenEquipadoCuerpo.getX();
									ultYCuerpo = imagenEquipadoCuerpo.getY();
									
								}
								encontrado = true;
								buscarItemEquipadoCuerpo = false;
								buscador = 0;
							} else {
								if(usuario.getInventario()[buscador + 1] != null) {
									buscador++;
								} else {
									((ItemEquipable) usuario.getInventario()[indiceItem]).setEquipado(true);
									((ItemEquipable) usuario.getInventario()[indiceItem]).setDesequipado(false);
									((ItemEquipable) usuario.getInventario()[indiceItem]).aplicarEfecto(usuario);
									imagenEquipadoCuerpo.setXY(usuario.getInventario()[indiceItem].getIcono().getX(),
											usuario.getInventario()[indiceItem].getIcono().getY());

									ultXCuerpo = imagenEquipadoCuerpo.getX();
									ultYCuerpo = imagenEquipadoCuerpo.getY();
									
									System.out.println("Equipe el item " + ((ItemEquipable) usuario.getInventario()[indiceItem]).getNombre());
									encontrado = true; // PARA FINALIZAR LA BUSQUEDA, YA QUE NO ENCONTRO NINGUN ITEM EQUIPADO.
									buscarItemEquipadoCuerpo = false;
									buscador = 0;
								}
							}
						}
					}
				}
			} else if (buscarItemEquipadoMano){
				while (!encontrado) {
					if (usuario.getInventario()[buscador] != null && !encontrado) {
						System.out.println(buscador);
						if(usuario.getInventario()[buscador].getTipo() == "consumible") {
							System.out.println("El item es consumible " + ((ItemConsumible)usuario.getInventario()[buscador]).getNombre());
							if(usuario.getInventario()[buscador + 1] != null) {
								System.out.println("Sigo buscando");
								buscador++;
							} else {
								((ItemEquipable) usuario.getInventario()[indiceItem]).setEquipado(true);
								((ItemEquipable) usuario.getInventario()[indiceItem]).setDesequipado(false);
								((ItemEquipable) usuario.getInventario()[indiceItem]).aplicarEfecto(usuario);
								imagenEquipadoMano.setXY(usuario.getInventario()[indiceItem].getIcono().getX(),
										usuario.getInventario()[indiceItem].getIcono().getY());

								ultXMano = imagenEquipadoMano.getX();
								ultYMano = imagenEquipadoMano.getY();
								
								System.out.println("Equipe el item " + ((ItemEquipable) usuario.getInventario()[indiceItem]).getNombre());
								encontrado = true; // PARA FINALIZAR LA BUSQUEDA, YA QUE NO ENCONTRO NINGUN ITEM EQUIPADO.
								buscarItemEquipadoMano = false;
								buscador = 0;
							}
						} else {
							if(((ItemEquipable) usuario.getInventario()[buscador]).isEquipado() && ((ItemEquipable) usuario.getInventario()[buscador]).getTipo() == "mano") {
								System.out.println("buscador " + buscador + "Encontre el item equipado " + ((ItemEquipable) usuario.getInventario()[buscador]).getNombre());
								((ItemEquipable) usuario.getInventario()[buscador]).setEquipado(false);
								((ItemEquipable) usuario.getInventario()[buscador]).setDesequipado(true);
								((ItemEquipable) usuario.getInventario()[buscador]).aplicarEfecto(usuario);
								System.out.println("Desequipé el item en uso " + ((ItemEquipable) usuario.getInventario()[buscador]).getNombre());
								imagenEquipadoMano.setXY(-100, -100);
								
								ultXMano = imagenEquipadoMano.getX();
								ultYMano = imagenEquipadoMano.getY();
								
								if(buscador != indiceItem) {
									((ItemEquipable) usuario.getInventario()[indiceItem]).setEquipado(true);
									((ItemEquipable) usuario.getInventario()[indiceItem]).setDesequipado(false);
									((ItemEquipable) usuario.getInventario()[indiceItem]).aplicarEfecto(usuario);
									System.out.println("Equipe el item " + ((ItemEquipable) usuario.getInventario()[indiceItem]).getNombre());
									imagenEquipadoMano.setXY(((ItemEquipable) usuario.getInventario()[indiceItem]).getIcono().getX(), ((ItemEquipable) usuario.getInventario()[indiceItem]).getIcono().getY());

									ultXMano = imagenEquipadoMano.getX();
									ultYMano = imagenEquipadoMano.getY();
									
								}
								encontrado = true;
								buscarItemEquipadoMano = false;
								buscador = 0;
							} else {
								if(buscador < 9 && usuario.getInventario()[buscador + 1] != null) {
									buscador++;
								} else {
									((ItemEquipable) usuario.getInventario()[indiceItem]).setEquipado(true);
									((ItemEquipable) usuario.getInventario()[indiceItem]).setDesequipado(false);
									((ItemEquipable) usuario.getInventario()[indiceItem]).aplicarEfecto(usuario);
									imagenEquipadoMano.setXY(usuario.getInventario()[indiceItem].getIcono().getX(),
											usuario.getInventario()[indiceItem].getIcono().getY());

									ultXMano = imagenEquipadoMano.getX();
									ultYMano = imagenEquipadoMano.getY();
									
									System.out.println("Equipe el item " + ((ItemEquipable) usuario.getInventario()[indiceItem]).getNombre());
									encontrado = true; // PARA FINALIZAR LA BUSQUEDA, YA QUE NO ENCONTRO NINGUN ITEM EQUIPADO.
									buscarItemEquipadoMano = false;
									buscador = 0;
								}
							}
						}
					}
				}
			}
			if(entradas.isT()) {
				tirarItem = true;
			}
			if(tirarItem) {
				tirarItemCartel.dibujar();
				cancelarBtn.dibujar();
				okBtn.dibujar();
			}
			if(BotonOk.tirarItemConfirmado) {
				if(usuario.getInventario()[indiceItem].getTipo() != "consumible" && ((ItemEquipable)usuario.getInventario()[indiceItem]).isEquipado()) {
					((ItemEquipable)usuario.getInventario()[indiceItem]).setEquipado(false);
					((ItemEquipable)usuario.getInventario()[indiceItem]).setDesequipado(true);
					((ItemEquipable)usuario.getInventario()[indiceItem]).aplicarEfecto(usuario);
					if(usuario.getInventario()[indiceItem].getTipo() == "cuerpo") {
						ultXCuerpo = -100;
						ultYCuerpo = -100;
					} else if(usuario.getInventario()[indiceItem].getTipo() == "mano") {
						ultXMano = -100;
						ultYMano = -100;
					}
				}
				usuario.getInventario()[indiceItem] = null;
				recuadroItem = false;
				tirarItem=false;
				BotonOk.tirarItemConfirmado = false;
			}
			if (ePresionada) {
				tiempoEspera += 0.1f;
				if (tiempoEspera >= 5f) {
					tiempoEspera = 0;
					ePresionada = false;
					cantUse = false;
				}
			}
			if (cantUse && !dibujar && !cofre) {
				noPuedesUsarItem.setTexto("Tu clase no puede utilizar este item");
				noPuedesUsarItem.setX(460);
				noPuedesUsarItem.setY(430);
				noPuedesUsarItem.dibujar();
			}
		} else {
			tiempoEspera=0;
			cantUse = false;
			ePresionada=false;
		}
		imagenEquipadoCuerpo.dibujar();
		imagenEquipadoMano.dibujar();
		b.end();
		sr.end();
	}

	private void reordenarItems(Jugador usuario) {
		for (int i = 0; i < usuario.getInventario().length; i++) {
			if(i>0 && usuario.getInventario()[i] != null && usuario.getInventario()[i - 1] == null) {
				usuario.getInventario()[i - 1] = usuario.getInventario()[i];
				usuario.getInventario()[i] = null;
			}
			if(i>0 && usuario.getInventario()[i-1] != null && usuario.getInventario()[i - 1].getTipo() != "consumible" && ((ItemEquipable)usuario.getInventario()[i-1]).isEquipado()) {
				if(usuario.getInventario()[i-1].getTipo() == "cuerpo") {
					ultXCuerpo = usuario.getInventario()[i-1].getX();
					ultYCuerpo = usuario.getInventario()[i-1].getY();
				} else if(usuario.getInventario()[i-1].getTipo() == "mano") {
					ultXMano = usuario.getInventario()[i-1].getX();
					ultYMano = usuario.getInventario()[i-1].getY();
				}
			}
		}
	}

	private void statsJugador() {
		vidaJugador.setTexto(usuario.getPersonaje().getVida() + " / " + usuario.getPersonaje().getVidaDefault());
		vidaJugador.dibujar();
		magiaJugador.setTexto(usuario.getPersonaje().getMagia() + " / " + usuario.getPersonaje().getMagiaDefault());
		magiaJugador.dibujar();
		fuerzaJugador.setTexto(String.valueOf(usuario.getPersonaje().getFuerza()));
		fuerzaJugador.dibujar();
		oroJugador.setTexto(String.valueOf(usuario.getCantOro()));
		oroJugador.dibujar();
	}

	private void recuadroItem() {
		int mouseX = entradas.getMouseX();
		int mouseY = entradas.getMouseY();
		
		for (int i = 0; i < usuario.getInventario().length; i++) {
			if (usuario.getInventario()[i] != null) {
				if (mouseX >= usuario.getInventario()[i].getIcono().getX()
						&& (mouseX <= usuario.getInventario()[i].getIcono().getX()
								+ usuario.getInventario()[i].getIcono().getWidth())) {
					if (mouseY >= usuario.getInventario()[i].getIcono().getY()
							&& (mouseY <= usuario.getInventario()[i].getIcono().getY()
									+ usuario.getInventario()[i].getIcono().getHeight())) {
						if (entradas.isClick()) {
							recuadroItem = true;
							indiceItem = i;
						}
					}
				}
			}
		}
	}

	private void crearPelea(World world, Jugador usuario) {
		Render.app.setScreen(new Batalla(this.world, this.usuario, b));
	}

	@Override
	public void resize(int width, int height) {
		gamePort.update(width, height);
	}

	@Override
	public void pause() {

	}

	@Override
	public void resume() {

	}

	@Override
	public void hide() {

	}

	@Override
	public void dispose() {
		b.dispose();
//		map.dispose();
//		renderer.dispose();
//		world.dispose();
//		b2dr.dispose();
//		hud.dispose();
	}

}
