db.auth('admuser', 'admpass')

db = db.getSiblingDB('stock_prices')

db.createUser({
    user: 'stock_prices_user',
    pwd: 'stock_prices_password',
    roles: [
        {
            role: 'root',
            db: 'admin',
        },
    ],
});