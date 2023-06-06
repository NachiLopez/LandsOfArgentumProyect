package tp3.lopez.nazareno.pantallas;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

import tp3.lopez.nazareno.inputoutput.Entradas;
import tp3.lopez.nazareno.items.ItemConsumible;
import tp3.lopez.nazareno.items.ItemEquipable;
import tp3.lopez.nazareno.jugadores.Cpu;
import tp3.lopez.nazareno.jugadores.Jugador;
import tp3.lopez.nazareno.personajes.Bardo;
import tp3.lopez.nazareno.personajes.Elfo;
import tp3.lopez.nazareno.personajes.Guerrero;
import tp3.lopez.nazareno.personajes.Mago;
import tp3.lopez.nazareno.personajes.Personaje;
import tp3.lopez.nazareno.ui.Boton;
import tp3.lopez.nazareno.ui.BotonAtacar;
import tp3.lopez.nazareno.ui.BotonInventario;
import tp3.lopez.nazareno.ui.BotonOk;
import tp3.lopez.nazareno.utiles.Config;
import tp3.lopez.nazareno.utiles.Imagen;
import tp3.lopez.nazareno.utiles.NombresRandom;
import tp3.lopez.nazareno.utiles.Render;
import tp3.lopez.nazareno.utiles.Texto;
import tp3.lopez.nazareno.utiles.Utiles;

public final class Batalla implements Screen {

	private int eleccionCpu;
	private Personaje pjCpu;
	private World world;
	private int turno, indice, indiceItem, buscador=0;
	private float tiempoEspera, timeAnimation, tiempoTextMagia, ultXCuerpo, ultYCuerpo, ultXMano, ultYMano;

	protected Jugador usuario;
	protected Jugador cpu;

	private int opcAtaque;
	private Texto[] opciones;
	private Texto esperandoText, noMagiaText, noPuedesUsarItem;
	private Imagen escenario, proximoMov, inventarioBackground, imagenEquipadoCuerpo, imagenEquipadoMano;
	private Boton atacarBtn, inventarioBtn, okBtn;
	private Entradas entradas = new Entradas();

	public static boolean atacarFunciones = false,inventarioFunciones = false, movimiento = true;
	private boolean seleccionado = false, noRepeatAttack = false, repeatForAnimation= false, noMagia=false, ePresionada=false, cantUse=false, recuadroItem = false, buscarItemEquipadoCuerpo=false, buscarItemEquipadoMano=false, encontrado= false;

	protected Stage stage;
	private Viewport viewport;
	private Label nombreLabel, nombreCpuLabel, vidaCpu, vidaUsuario, magiaCpu, magiaUsuario;

	private SpriteBatch b;
	private ShapeRenderer sr = Render.sr;

	public Batalla(World world, Jugador usuario, SpriteBatch b) {
		this.world = world;
		this.usuario = usuario;
		opciones = new Texto[usuario.getPersonaje().getCantAtk()];

		viewport = new FitViewport(Config.ANCHO, Config.ALTO, new OrthographicCamera());
		stage = new Stage(viewport);

		nombreLabel = new Label(usuario.getNombre(), new Label.LabelStyle(new BitmapFont(), Color.WHITE));
		nombreLabel.setPosition(290, 330);
		nombreLabel.setFontScaleX(1f);
		nombreLabel.setFontScaleY(1f);
		stage.addActor(nombreLabel);

		vidaUsuario = new Label(("Vida: " + usuario.getPersonaje().getVida() + " / " + usuario.getPersonaje().getVidaDefault()),
				new Label.LabelStyle(new BitmapFont(), Color.WHITE));
		vidaUsuario.setPosition(285, 430);
		vidaUsuario.setFontScaleX(1f);
		vidaUsuario.setFontScaleY(1f);
		magiaUsuario = new Label(("Magia: " + usuario.getPersonaje().getMagia() + " / " + usuario.getPersonaje().getMagiaDefault()),
				new Label.LabelStyle(new BitmapFont(), Color.WHITE));
		magiaUsuario.setPosition(285, 410);
		magiaUsuario.setFontScaleX(1f);
		magiaUsuario.setFontScaleY(1f);
		stage.addActor(vidaUsuario);
		stage.addActor(magiaUsuario);
	}

	@Override
	public void show() {
		b = Render.batch;
		Gdx.input.setInputProcessor(entradas);

		eleccionCpu = Utiles.r.nextInt(4);
//		eleccionCpu = 2;
		
		if (eleccionCpu == 0) {
			pjCpu = new Mago(world);
		} else if (eleccionCpu == 1) {
			pjCpu = new Guerrero(world);
		} else if (eleccionCpu == 2) {
			pjCpu = new Elfo(world);
		}
		else if (eleccionCpu == 3) {
			pjCpu = new Bardo(world);
		}
		cpu = new Cpu(NombresRandom.getNombreRandom(), pjCpu);
		
		turno = Utiles.r.nextInt(2);

		esperandoText = new Texto("Chomsky.otf", 16, Color.WHITE, true);
		esperandoText.setTexto("Esperando al enemigo");
		esperandoText.setX(Config.ANCHO / 2 - 100);
		esperandoText.setY(Config.ALTO / 2);
		
		noMagiaText = new Texto("Chomsky.otf", 16, Color.WHITE, true);
		noMagiaText.setTexto("No" + " tienes" + " suficiente" + " magia");
		noMagiaText.setX(235);
		noMagiaText.setY(470);
		
		nombreCpuLabel = new Label(cpu.getNombre(), new Label.LabelStyle(new BitmapFont(), Color.WHITE));
		nombreCpuLabel.setPosition(990, 330);
		nombreCpuLabel.setFontScaleX(1f);
		nombreCpuLabel.setFontScaleY(1f);
		vidaCpu = new Label(("Vida: " + cpu.getPersonaje().getVida()),
				new Label.LabelStyle(new BitmapFont(), Color.WHITE));
		vidaCpu.setPosition(985, 430);
		vidaCpu.setFontScaleX(1f);
		vidaCpu.setFontScaleY(1f);
		stage.addActor(vidaCpu);
		stage.addActor(nombreCpuLabel);
		magiaCpu = new Label(("Magia: " + cpu.getPersonaje().getMagia()),
				new Label.LabelStyle(new BitmapFont(), Color.WHITE));
		magiaCpu.setPosition(985, 410);
		magiaCpu.setFontScaleX(1f);
		magiaCpu.setFontScaleY(1f);
		stage.addActor(magiaCpu);

		escenario = new Imagen("pantallas/escenarioBatalla.png");
		proximoMov = new Imagen("display/proximoMov.png");
		proximoMov.setXY(Config.ANCHO / 2 - proximoMov.getWidth() / 2, Config.ALTO / 2 - proximoMov.getHeight() / 2);
		inventarioBackground = new Imagen("display/inventarioBackground.png");
		inventarioBackground.setXY(Config.ANCHO / 2 - inventarioBackground.getWidth() / 2, Config.ALTO / 2 - inventarioBackground.getHeight() / 2);
		okBtn = new BotonOk(entradas, (Config.ANCHO / 2.2f) - 15, (Config.ALTO / 2.08f) - 110);
		atacarBtn = new BotonAtacar(entradas, (Config.ANCHO / 2.2f), (Config.ALTO / 2.08f));
		inventarioBtn = new BotonInventario(entradas);
		noPuedesUsarItem = new Texto("Adventure Script.otf", 19, Color.WHITE, true);

		imagenEquipadoCuerpo = new Imagen("display/imagenEquipado.png");
		imagenEquipadoMano = new Imagen("display/imagenEquipado.png");
		
		for (int i = 0; i < usuario.getInventario().length; i++) {
			if(usuario.getInventario()[i] != null) {
				usuario.getInventario()[i].setX(520);
				usuario.getInventario()[i].setY(450);
			}
		}
	}

	@Override
	public void render(float delta) {
		Render.limpiarPantalla(0, 0, 0, 1);
		Personaje player = usuario.getPersonaje();
		Personaje cpuPlayer = cpu.getPersonaje();

		verStats();

		for (int i = 0; i < usuario.getInventario().length; i++) {
			if(usuario.getInventario()[i] != null && usuario.getInventario()[i].getTipo() != "consumible" && ((ItemEquipable)usuario.getInventario()[i]).isEquipado()) {
				if(usuario.getInventario()[i].getTipo() == "cuerpo") {
					ultXCuerpo = usuario.getInventario()[i].getX();
					ultYCuerpo = usuario.getInventario()[i].getY();
				} else if(usuario.getInventario()[i].getTipo() == "mano") {
					ultXMano= usuario.getInventario()[i].getX();
					ultYMano= usuario.getInventario()[i].getY();
				}
			}
		}
		
		sr.begin(ShapeType.Line);
		b.begin();
		escenario.dibujar();
		b.draw(player.getPjQuieto(), 300 / Config.PPM, 350 / Config.PPM);
		b.draw(cpuPlayer.getPjQuieto(), 1000 / Config.PPM, 350 / Config.PPM);
		cpuPlayer.setX(950 / Config.PPM);
		cpuPlayer.setY(350 / Config.PPM);
		player.setX(250 / Config.PPM);
		player.setY(350 / Config.PPM);

		if (turno == 0) {
			if (player.getVida() > 0) {
				proximoMov.dibujar();
				if (movimiento) {
					atacarBtn.dibujar();
					inventarioBtn.dibujar();
				} else if(inventarioFunciones) {
					atacarFunciones = false;
					movimiento = false;
					inventarioBackground.dibujar();
					for (int i = 0; i < usuario.getInventario().length; i++) {
						if (usuario.getInventario()[i] != null) {
							if (i != 0 && usuario.getInventario()[i - 1] != null && usuario.getInventario()[i].getX() == usuario.getInventario()[i - 1].getX()) {
								usuario.getInventario()[i].setX(usuario.getInventario()[i - 1].getX() + 40);
								if (i < 9 && usuario.getInventario()[i + 1] != null) {
									usuario.getInventario()[i + 1].setX(usuario.getInventario()[i].getX());
									usuario.getInventario()[i + 1].setY(usuario.getInventario()[i].getY());
								}
							}
							if (usuario.getInventario()[i].getX() >= (inventarioBackground.getX() + inventarioBackground.getWidth() - 40)){
								usuario.getInventario()[i].setX(520);
								usuario.getInventario()[i].setY(450 - 40);
								if (usuario.getInventario()[i + 1] != null) {
									usuario.getInventario()[i + 1].setX(usuario.getInventario()[i].getX());
									usuario.getInventario()[i + 1].setY(usuario.getInventario()[i].getY());
								}
							}
							usuario.getInventario()[i].dibujar(usuario.getInventario()[i].getX(), usuario.getInventario()[i].getY());
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
							System.out.println(tiempoEspera);
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
										if(usuario.getInventario()[indiceItem + 1] != null) {
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
											if(usuario.getInventario()[buscador + 1] != null) {
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
						if (ePresionada) {
							tiempoEspera += 0.1f;
							System.out.println(tiempoEspera);
							if (tiempoEspera >= 5f) {
								tiempoEspera = 0;
								System.out.println("entre");
								cantUse = false;
								ePresionada = false;
							}
						}
						if (cantUse) {
							noPuedesUsarItem.setTexto("Tu clase no puede utilizar este item");
							noPuedesUsarItem.setX(530);
							noPuedesUsarItem.setY(580);
							noPuedesUsarItem.dibujar();
						}
					} else {
						tiempoEspera=0;
						tiempoTextMagia=0;
						cantUse = false;
						ePresionada=false;
					}
					okBtn.dibujar();
					imagenEquipadoCuerpo.setXY(ultXCuerpo, ultYCuerpo);
					imagenEquipadoMano.setXY(ultXMano, ultYMano);
					imagenEquipadoCuerpo.dibujar();
					imagenEquipadoMano.dibujar();
				} else if (atacarFunciones) {
					inventarioFunciones = false;
					movimiento = false;
					posicionarTextoAtaque();
					seleccionarAtaque(delta);
				}
			} else {
				Render.app.setScreen(new GameOver());
			}
		} else if (turno == 1) {
			if (cpuPlayer.getVida() > 0) {
				atacarFunciones = false;
				inventarioFunciones = false;
				movimiento = false;
				esperandoText.dibujar();
				tiempoEspera += 0.1f;
				if(tiempoEspera>=5f && !repeatForAnimation) {
					tiempoEspera = 0;
					opcAtaque = Utiles.r.nextInt(0, cpuPlayer.getCantAtk());
					while(cpuPlayer.getMagia()<cpuPlayer.getAtaque(opcAtaque).getMagia()){
						opcAtaque = Utiles.r.nextInt(0, cpuPlayer.getCantAtk());
					}
					ejecutarAtaque(cpuPlayer, player, opcAtaque, delta);
					repeatForAnimation = true;
				}
				if(repeatForAnimation) {
					ejecutarAtaque(cpuPlayer, player, opcAtaque, delta);
				}
			} else {
				Render.app.setScreen(new YouWin(usuario));
			}
		}
		stage.draw();
		b.end();
		sr.end();
	}
	
	private void recuadroItem() {
		int mouseX = entradas.getMouseX();
		int mouseY = entradas.getMouseY();

		for (int i = 0; i < usuario.getInventario().length; i++) {
			if (usuario.getInventario()[i] != null) {
				if(usuario.getInventario()[i].getTipo() == "cuerpo" && ((ItemEquipable)usuario.getInventario()[i]).isEquipado()) {
					imagenEquipadoCuerpo.setXY(usuario.getInventario()[i].getX(), usuario.getInventario()[i].getY());
				} else if(usuario.getInventario()[i].getTipo() == "mano" && ((ItemEquipable)usuario.getInventario()[i]).isEquipado()) {
					imagenEquipadoMano.setXY(usuario.getInventario()[i].getIcono().getX(), usuario.getInventario()[i].getIcono().getY());
				}
				
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
	
	private void seleccionarAtaque(float delta) {
		Personaje player = usuario.getPersonaje();
		Personaje cpuPlayer = cpu.getPersonaje();
		int mouseX = entradas.getMouseX();
		int mouseY = entradas.getMouseY();

		if (turno == 0) {
			if(!seleccionado) { // Si no seleccione el ataque, puedo seleccionarlo
				for (int i = 0; i < opciones.length; i++) {
					if (mouseX >= opciones[i].getX() && (mouseX <= (opciones[i].getX() + opciones[i].getAncho()))) {
						if (mouseY >= opciones[i].getY() - 20 && (mouseY <= (opciones[i].getY() + opciones[i].getAlto() - 20))) {
							indice = i;
							if (entradas.isClick()) {
								if(player.getMagia()>=player.getAtaque(indice).getMagia()) {
									ejecutarAtaque(player, cpuPlayer, indice, delta);
									seleccionado = true;
									noMagia=false;
									tiempoTextMagia = 0;
								} else {
									noMagia=true;
								}
							}
						}
					}
				}
				if(noMagia) {
					noMagiaText.dibujar();
					tiempoTextMagia += 0.1f;
					if(tiempoTextMagia>=5f) {
						tiempoTextMagia = 0;
						noMagia = false;
					}
				}
			}else if(seleccionado) { // Si seleccione el ataque, no puedo elegir otro pero se sigue ejecutando hasta que termine la animacion del ataque
				ejecutarAtaque(player, cpuPlayer, indice, delta);
			}
		}
		
	}
		
	private void ejecutarAtaque(Personaje atacante, Personaje contrincante, int indice, float delta) {
		float tiempoNecesario = atacante.getAtaque(indice).getTimeAnimation();
		
		if(!noRepeatAttack) {
			noRepeatAttack = true;
			
			if (atacante.getAtaque(indice).getNombre().equals("Magia blanca")) {
				int cantVida = Utiles.r.nextInt(100, 300) + 1;
				atacante.getAtaque(indice).setX(atacante.getX());
				atacante.getAtaque(indice).setY(atacante.getY());
				atacante.setVida(atacante.getVida() + cantVida);
				if (atacante.getVida() > atacante.getVidaDefault()) {
					atacante.setVida(atacante.getVidaDefault());
				}
			} else {
				atacante.getAtaque(indice).setX(contrincante.getX());
				atacante.getAtaque(indice).setY(contrincante.getY());
			}
			
			int dañoMin = atacante.getAtaque(indice).getDañoMin();
			int dañoMax = atacante.getAtaque(indice).getDañoMax();
			int daño = Utiles.r.nextInt(dañoMin, dañoMax) + 1;

			if (atacante.getAtaque(indice).isFisico()) {
				daño *= atacante.getFuerza();
			} else {
				atacante.setMagia(atacante.getMagia() - atacante.getAtaque(indice).getMagia());
			}
			contrincante.setVida(contrincante.getVida() - daño);
		}
		atacante.getAtaque(indice).dibujar(b);
		if(timeAnimation>=tiempoNecesario) {
			timeAnimation=0;
			seleccionado = false;
			noRepeatAttack = false;
			repeatForAnimation = false;
			atacarFunciones = false;
			inventarioFunciones = false;
			if (turno==0) {
				turno = 1; 
				movimiento = false;
			} else if(turno==1) { 
				turno = 0;
				movimiento = true;
			}
		}
		timeAnimation += Gdx.graphics.getDeltaTime();
	}

	private void posicionarTextoAtaque() {
		Personaje player = usuario.getPersonaje();

		float y = 420;
		int anterior = 0;
		for (int i = 0; i < opciones.length; i++) {
			opciones[i] = new Texto("Berkahi Blackletter.otf", 16, Color.WHITE, true);
			opciones[i].setTexto(i + 1 + ") " + player.getAtaque(i).getNombre());
			opciones[i].setX(580);
			opciones[i].setY(y);
			if (i > 0 && opciones[i].getY() == opciones[anterior].getY()) {
				y -= 30;
				opciones[i].setY(y);
				anterior = i;
			}
			opciones[i].dibujar();
		}
	}
	
	public void verStats() {
		vidaCpu.setText("Vida: " + cpu.getPersonaje().getVida() + " / " + cpu.getPersonaje().getVidaDefault());
		magiaCpu.setText("Magia: " + cpu.getPersonaje().getMagia() + " / " + cpu.getPersonaje().getMagiaDefault());
		vidaUsuario.setText("Vida: " + usuario.getPersonaje().getVida() + " / " + usuario.getPersonaje().getVidaDefault());
		magiaUsuario.setText("Magia: " + usuario.getPersonaje().getMagia() + " / " + usuario.getPersonaje().getMagiaDefault());
	}

	@Override
	public void resize(int width, int height) {

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

	public void dispose() {
		this.dispose();
	}

}
