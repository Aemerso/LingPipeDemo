The GetNewPoems class includes a main function that when executed
will use Selenium WebDriver to select 50 random poems from the
site http://www.public-domain-poetry.com/
Each poem is saved in a numbered text file in the resources folder.

DO NOT run GetNewPoems without first ensuring the "TestPoems" Directory
is empty or I/O errors may occur

The RegressionTest class tests the poems stored in the various classification
folders to ensure accuracy in classifying. As of 5/22/2017 the regression test
shows thatfurther data must beadded to these folders to get accurate results

The ClassifyPoems class utilizes the classification directories (DarkPoems, 
LovePoems, SadPoems) to train the system, then looks to the TestPoems directory
to test the poems saved there.