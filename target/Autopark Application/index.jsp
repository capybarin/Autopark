<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Home</title>
    <link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
    <style>
        h1 {text-align: right;}

        footer {
            margin-top: 21%;
            padding: 20px 50px;
            background-color: #9aabb4;
            color: #fff;
        }

        .wall {
            margin: 2% 10% 0 10%;
            display: grid;
            grid-template-columns: 50% 50%;
            text-align: center;
        }
        .wall_1, .wall_2 {
            display: flex;
            justify-content: center;
            flex-direction: column;
            max-width: 477px;
            margin: auto;
        }
        .btn_st {
            width: 140px;
            margin: 40px auto;
            background-color: #56db5a;
            color: #fff;
            box-shadow: 0 8px 16px 0 rgba(0,0,0,0.1),0 6px 20px 0 rgba(0,0,0,0.1)
        }
        .b_img {
            background: url(https://smartrak.com/wp-content/uploads/2019/10/Cars.jpg);
            background-repeat: no-repeat;
            background-size: cover;
            background-color: #fff;
        }



        .wall_1 {padding: 10% 0;}
        .mar {margin: 5%;}
        .wall_brad {border-radius: 14px;}
        .img_right {padding: 10% 0; width: 80%;}


    </style>
</head>
<body class="w3-light-grey">
<div class="w3-container w3-blue-grey w3-opacity w3-right-align">
    <h1>Motor pool application</h1>
</div>
<div class="wall">
    <div class="wall_1">
        <!--button class="btn_st w3-btn w3-hover-green w3-round-large" onclick="location.href='/list'">List of users</button!-->
        <button class="btn_st w3-btn w3-hover-green w3-round-large" onclick="location.href='/add'">Sign up</button>
        <button class="btn_st w3-btn w3-hover-green w3-round-large" onclick="location.href='/login'">Login</button>
    </div>
    <div class="wall_2 b_img mar wall_brad">
    </div>
</div>
<footer>
    <p>(c)Vladyslav Bezdushnyi, 2020</p>
</footer>
</body>
</html>