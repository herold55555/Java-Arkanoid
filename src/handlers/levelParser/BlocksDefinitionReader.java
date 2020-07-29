package handlers.levelParser;


/**
 * The type Blocks definition reader.
 */
public class BlocksDefinitionReader {

    /**
     * From reader blocks from symbols factory.
     *
     * @param reader the reader
     * @return the blocks from symbols factory
     * @throws Exception the exception
     */
    public static BlocksFromSymbolsFactory fromReader(String reader) throws Exception {
        BlocksFromSymbolsFactory factory = new BlocksFromSymbolsFactory(reader);
        factory.startAnalize();
        return factory;
    }
}
