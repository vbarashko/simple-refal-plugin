# Simple Refal Plugin for IntelliJ IDEA
Simple Refal Plugin adds [Simple Refal](https://github.com/bmstu-iu9/simple-refal/) Language support to [IntelliJ IDEA](http://www.jetbrains.com/idea/).

# Features
* Data Type Auto-completion
* Keyword highlighting
* Code commenting/uncommenting
* Brace matching
* Syntax and errors highlighting
* Custom Color Settings Page

# Installation
Import [simple-refal-plugin.jar](https://github.com/vlbar/simple-refal-plugin/blob/master/simple-refal-plugin.jar) from this repo to Intellij IDEA.

## Authors
* Vladimir Barashko
* Alexander Konovalov

## How to get started developing
1. Check out with git
2. Set up intellij for plugin development http://www.jetbrains.org/intellij/sdk/docs/basics/getting_started.html
3. Install grammar kit plugin
4. DO NOT Install jflex plugin.  The grammar kit plugin is sufficient.
5. "Generate parser code" by right clicking on the SimpleRefal.bnf file
6. "Run jflex generator" by right clicking on the SimpleRefal.flex file
7. Go through the Intellij tutorial if you get stuck (http://www.jetbrains.org/intellij/sdk/docs/tutorials/custom_language_support_tutorial.html)


## Change log

### Version 1.1:
* Initial pattern term autocompetion 
* Minor fixes