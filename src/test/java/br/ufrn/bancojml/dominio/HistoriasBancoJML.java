package br.ufrn.bancojml.dominio;

import org.jbehave.core.configuration.Configuration;
import org.jbehave.core.configuration.Keywords;
import org.jbehave.core.configuration.MostUsefulConfiguration;
import org.jbehave.core.failures.RethrowingFailure;
import org.jbehave.core.i18n.LocalizedKeywords;
import org.jbehave.core.io.CodeLocations;
import org.jbehave.core.io.LoadFromClasspath;
import org.jbehave.core.io.StoryFinder;
import org.jbehave.core.junit.JUnitStories;
import org.jbehave.core.reporters.FilePrintStreamFactory;
import org.jbehave.core.reporters.PrintStreamStepdocReporter;
import org.jbehave.core.reporters.StoryReporterBuilder;
import org.jbehave.core.steps.CandidateSteps;
import org.jbehave.core.steps.InjectableStepsFactory;
import org.jbehave.core.steps.InstanceStepsFactory;

import java.net.URL;
import java.util.List;
import java.util.Locale;
import java.util.Properties;

import static org.jbehave.core.io.CodeLocations.codeLocationFromClass;
import static org.jbehave.core.reporters.Format.*;

public class HistoriasBancoJML extends JUnitStories {

    @Override
    public Configuration configuration() {
        ClassLoader classLoader = this.getClass().getClassLoader();
        URL codeLocation = CodeLocations.codeLocationFromClass(this.getClass());
        Keywords ptbrKeywords = new LocalizedKeywords(locale());
        Properties properties = new Properties();
        properties.setProperty("encoding", "UTF-8");
        properties.setProperty("reports", "ftl/jbehave-reports.ftl");

        return new MostUsefulConfiguration()
                .useKeywords(ptbrKeywords)
                .useStoryLoader(new LoadFromClasspath(classLoader))
                .useStoryReporterBuilder(
                    new StoryReporterBuilder()
                            .withCodeLocation(codeLocation)
                            .withPathResolver(new FilePrintStreamFactory.ResolveToSimpleName())
                            .withDefaultFormats()
                            .withFormats(CONSOLE, TXT, HTML, XML)
                            .withFailureTrace(true)
                            .withViewResources(properties)
                            .withKeywords(ptbrKeywords)
                )
                .useFailureStrategy(new RethrowingFailure())
                .useStepdocReporter(new PrintStreamStepdocReporter());
    }

    public InjectableStepsFactory stepsFactory() {
        return new InstanceStepsFactory(
                        configuration(),
                        new PassosCartaoDeCredito()
                );
    }

    public List<CandidateSteps> candidateSteps() {
        return new InstanceStepsFactory(
                    configuration(),
                    new PassosCartaoDeCredito(),
                    new PassosFatura()
                )
                .createCandidateSteps();
    }

    public List<String> storyPaths() {
        List<String> stories = new StoryFinder().findPaths(codeLocationFromClass(this.getClass()),
                storyPattern(), "**/excluida*.historia");
        return stories;
    }

    protected Locale locale() {
        return new Locale("pt");
    }

    protected String storyPattern() {
        return "**/*.historia";
    }

}