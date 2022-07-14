Feature: Select the DSL 
@Task1
Scenario: I can select the best available internet tariff for my needs
	Given that I can open "www.verivox.de"
	When I navigate to the DSL calculator page
	And I enter "030" for my area code
	And I select the 100 Mbit/s bandwidth option
	And I click the "Jetzt vergleichen" button
	Then I should see a page that lists the available tariffs for my selection

@Task2
Scenario: Load multiple tariff result pages
	Given the same tariff calculation criteria from scenario 1
	When I display the tariff Result List page
	Then I should see the total number of available tariffs listed in the "Ermittelte Tarife" section
	When I scroll to the end of the Result List page
	Then I should see only the first 20 tariffs displayed
	When  I click on the button labeled "20 weitere Tarife laden"
	Then I should see the next 20 tariffs displayed
	And I can continue to load any additional tariffs until all tariffs have been displayed