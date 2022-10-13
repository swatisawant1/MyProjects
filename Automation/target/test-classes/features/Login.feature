Feature: Application Login

Scenario: Home page default login
Given User is on NetBanking landing page
When User login into application with "demo" and password "quick@123"
Then Home page is populated
And Cards are displayed "true"

Scenario: Home page default login
Given User is on NetBanking landing page
When User login into application with "Chemo" and password "fast@123"
Then Home page is populated
And Cards are displayed "false"
