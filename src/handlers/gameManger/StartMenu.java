package handlers.gameManger;

import graphics.background.BackgroundGenerator;
import graphics.levelData.LevelInformation;
import graphics.screens.Exit;
import graphics.screens.MenuAnimation;
import graphics.screens.ShowGame;
import graphics.screens.ShowHiScoresTask;
import handlers.graphic.AnimationRunner;
import handlers.levelParser.LevelSpecificationReader;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.List;

/**
 * The type Start menu.
 */
public class StartMenu {

    private AnimationRunner animationRunner;
    private Reader reader;

    /**
     * Instantiates a new Start menu.
     *
     * @param runner the runner
     * @param file   the file
     */
    public StartMenu(AnimationRunner runner, Reader file) {
        animationRunner = runner;
        reader = file;
    }

    /**
     * Open menu.
     */
    public void openMenu() {
        Menu<Task<Void>> menu = new MenuAnimation<>("Arknoid", animationRunner,
                BackgroundGenerator.createNewBackground());
        LevelMenu levelMenu = new LevelMenu("Choose Level", animationRunner,
                BackgroundGenerator.createNewBackground());
        readLevels(levelMenu);
        menu.addSelection("s", "Start game", levelMenu);
        menu.addSelection("h", "High scores", new ShowHiScoresTask(animationRunner));
        menu.addSelection("q", "quit", new Exit(animationRunner));
        while (true) {
            animationRunner.run(menu);
            Task<Void> task = menu.getStatus();
            task.run();
        }
    }

    /**
     * read levels.
     * @param menu menu
     */
    private void readLevels(LevelMenu menu) {
        BufferedReader br = new BufferedReader(reader);
        String currLine;
        LevelSpecificationReader levelReader = new LevelSpecificationReader();
        try {
            while ((currLine = br.readLine()) != null) {
                if (!currLine.contains(":")) {
                    throw new Exception("error in line");
                }
                String[] parts = currLine.trim().split(":");
                currLine = br.readLine();
                Reader pathToLevel = new InputStreamReader(ClassLoader.getSystemClassLoader().
                        getResourceAsStream(currLine));
                List<LevelInformation> levels = levelReader.fromReader(pathToLevel);
                ShowGame game = new ShowGame(animationRunner, levels);
                menu.addSelection(parts[0], parts[1], game);
            }
        } catch (Exception e) {
            e.getStackTrace();
        }
    }
}
