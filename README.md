# lauzhack-2016
Lauzhack hackathon

# System Configuration
You shoud have installed virtualenv:
```
$ sudo pip install virtualenv
```

Enter in the root folder of this project:
```
$ cd lauzhack-2016
```

set up a new virtual environment:

```
$ virtualenv env
```

You can choose another name for the environment instead of “env”, but you should add it in your .gitignore then.

To start the above environment we just need to run the following command:
```
$ . env/bin/activate
```

In our venv we will first install Flask framework:
```
(venv) lauzhack-2016 $ pip install Flask
``` 

# Start the API Server
```
(venv) lauzhack-2016 $ python whatthecrop.py
``` 
