# RIdiom Description

Refactoring non-idiomatic Python code into idiomatic code with Python idioms


## Installation

- Install RIdiom from Disk
  
  Download RIdiom-6.0-SNAPSHOT.zip 
  
- Market:
  
  https://plugins.jetbrains.com/plugin/20107-ridiom
  
  
## Usage

  ### You can refer to the video to learn how to use the tool [Video URL](https://youtu.be/45GRKGBSoD4)

  1. After the successful installation, we right-click a folder or a file you want to refactor and then click RIdiom in the menu bar.

  2. We first need to set the python compiler, its version must be above 3.9. We also need to install two packages: sympy and pathos.
  
  3. Then we click RIdiom and the preview button to show details of all refactorable code snippets with selected Python idioms. We could click the row of a code pair to navigate to the specified file location. If we don’t want to refactor some non-idiomatic code fragments, we could select them and then click the remove button. 
  
  4. Finally, we click the refactor button to replace the non-idiomatic code with the idiomatic code in the folder or the file. 
  

## Python Package: 

We develop a Python package, you could install the package from https://pypi.org/project/RefactoringIdioms/

## Web application:

We also develop a web application for the code refactoring, you could access the application through the url: 47.242.131.128:5000

Each time, you could click code area to refresh.


## How to cite
If you are using RIdiom in your research, please cite the following paper:

Zejun Zhang, Zhenchang Xing, Xin Xia, Xiwei Xu, and Liming Zhu, “Making python code idiomatic by automatic refactoring non-idiomatic python code with pythonic idioms,” arXiv preprint arXiv:2207.05613, 2022

@article{zhang2022making,
  title={Making Python Code Idiomatic by Automatic Refactoring Non-Idiomatic Python Code with Pythonic Idioms},
  author={Zhang, Zejun and Xing, Zhenchang and Xia, Xin and Xu, Xiwei and Zhu, Liming},
  journal={arXiv preprint arXiv:2207.05613},
  year={2022}
}

---
Plugin based on the [IntelliJ Platform Plugin Template][template].

[template]: https://github.com/JetBrains/intellij-platform-plugin-template
