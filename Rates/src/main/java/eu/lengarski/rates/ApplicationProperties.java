package eu.lengarski.rates;


import org.hibernate.engine.jdbc.batch.internal.AbstractBatchImpl;
import org.hibernate.engine.jdbc.batch.spi.Batch;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "application", ignoreUnknownFields = false)
public class ApplicationProperties {

    private final Batch batch = new Batch();

    public Batch getBatch() {
        return this.batch;
    }

    private static class Batch {

        private String inputPath = "";

        public String getInputPath() {
            return inputPath;
        }

        public void setInputPath(String inputPath) {
            this.inputPath = inputPath;
        }

        protected void doExecuteBatch() {

        }


        public void addToBatch() {

        }
    }

    ;
}
