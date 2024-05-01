package utilities;


import logger.LOG;
import net.rcarz.jiraclient.*;
import net.rcarz.jiraclient.Issue.FluentCreate;

import java.util.HashSet;
import java.util.Set;

public class JiraServiceProvider {

    public JiraClient jira;
    private static final String PROJECT_KEY = "GP";
    Set<String> labels = new HashSet<>();
    public String jiraUrl="https://appinventivtech.atlassian.net/";


    public JiraServiceProvider(String jiraUrl, String username, String password) {
        BasicCredentials credentials = new BasicCredentials(username, password);
        jira = new JiraClient(jiraUrl,credentials);

    }

    public void createJiraTicket(String issueType, String summary, String description) {
        try {
            Issue.SearchResult sr = jira.searchIssues("summary ~ \"" + summary + "\"");
            if (sr.total != 0) {
                LOG.INFO("Same Issue Already Exists on Jira");
                return;
            }
            FluentCreate fluentCreate = jira.createIssue(PROJECT_KEY, issueType);
            fluentCreate.field(Field.SUMMARY, summary);
            fluentCreate.field(Field.DESCRIPTION, description);
            labels.add("Automation");
            Issue newIssue = fluentCreate.execute();
            newIssue.update().field(Field.LABELS, labels).execute();
            LOG.INFO("New issue created in Jira with ID : " + newIssue);
            LOG.INFO("New issue URL is :" + jiraUrl + "/browse/" + newIssue);
        } catch (JiraException e) {
            e.printStackTrace();
        }
    }

}

