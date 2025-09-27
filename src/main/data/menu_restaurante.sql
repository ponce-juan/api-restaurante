-- ==========================================
-- COMPANIAS
-- ==========================================
INSERT INTO companies (name, tables_count) VALUES
    ('Resto-Pub', 10),
    ('La Vendetta', 15);

-- ==========================================
-- USERS
-- ==========================================
INSERT INTO users (username, password, company_id) VALUES
    ('admin', '$2a$10$25hFXhZaCWsGJdtazgmv2ul6surOwblmp6dxkSruzjUoBlU6PL7fS', 1),
    ('mozo1', '$2a$10$xEX47jmyAqqXwJb.vva./OPXBn/pHGwx53jfCHtDUzW6KBU2FiRom', 1),
    ('supervisor1', '$2a$10$GJ3MENd.8vQ8opPqAPwdz.sO9MhxSbGnUPVzEwnekbCI0JhXhPnly', 1),
    ('mozo2', '$2a$10$d2d2uRnotSM8/1vXjUvXre8Mb5ikDk00DLrAu33YXz6U72c8r6URi', 2),
    ('supervisor2', '$2a$10$ikcyGMlFr4yi03dqs4T4I.M1ZdnrBfTClFgtPZVro665/U.QDCoAW', 2);

-- ==========================================
-- EMPLOYEES
-- ==========================================
INSERT INTO employees (city, country, number, state, street, dni, email, last_name, name, phone, role, user_id) VALUES
    ('Rosario', 'Argentina', 200, 'Santa Fe', 'Bv. Oroño', '30000207', 'admin@admin.com', 'Boss', 'Your', '123123123', 'ADMIN', 1),
    ('Rosario', 'Argentina', 1500, 'Santa Fe', 'Rioja', '30000201', 'mozo1@resto1.com', 'Rodriguez', 'Pablo', '12333322', 'MOZO', 2),
    ('Rosario', 'Argentina', 700, 'Santa Fe', 'Cordoba', '30000202', 'supervisor1@resto1.com', 'Castillo', 'Jesica', '14222333', 'SUPERVISOR', 3),
    ('Rosario', 'Argentina', 400, 'Santa Fe', 'Ov. Lagos', '30000220', 'mozo2@resto2.com', 'Castro', 'Lucas', '123123000', 'MOZO', 4),
    ('Rosario', 'Argentina', 700, 'Santa Fe', 'Av. San Martin', '30020117', 'supervisor2@resto2.com', 'Perez', 'Julio',
    '3413413414', 'SUPERVISOR', 5);

-- ==========================================
-- CATEGORÍAS
-- ==========================================
INSERT INTO categories (id, name) VALUES
(1, 'Pastas'),
(2, 'Carnes'),
(3, 'Bebidas'),
(4, 'Postres'),
(5, 'Entradas'),
(6, 'Pizzas'),
(7, 'Hamburguesas');

-- ==========================================
-- SUBCATEGORÍAS
-- ==========================================
INSERT INTO subcategories (id, name) VALUES
-- Generales
(1, 'Menú del día'),
(2, 'Promociones'),
(3, 'Desayunos'),
(4, 'Meriendas'),
(5, 'Veganos'),
(6, 'Sin TACC'),
(7, 'Infantiles');

-- Pastas
(8, 'Salsas'),
(9, 'Pastas rellenas'),
(10, 'Pastas secas'),

-- Carnes
(11, 'Parrilla'),
(12, 'Aves'),
(13, 'Mariscos'),

-- Bebidas
(14, 'Gaseosas'),
(15, 'Vinos'),
(16, 'Cervezas'),
(17, 'Aguas'),

-- Postres
(18, 'Helados'),
(19, 'Tortas'),
(20, 'Tradicionales'),

-- Entradas
(21, 'Picadas'),
(22, 'Empanadas'),
(23, 'Sopas'),

-- Pizzas
(24, 'Clásicas'),
(25, 'Especiales'),

-- Hamburguesas
(26, 'Clásicas'),
(27, 'Gourmet');

-- ==========================================
-- PRODUCTOS
-- ==========================================
INSERT INTO products (id, name, description, price, stock, category_id, subcategory_id, company_id) VALUES
-- Pastas
(1, 'Tallarines c/bolognesa', 'Tallarines frescos con salsa bolognesa casera', 13000, 100, 1, 8, 1),
(2, 'Ravioles de ricota y nuez', 'Ravioles caseros con salsa fileto', 14500, 80, 1, 9, 2),
(3, 'Ñoquis de papa', 'Ñoquis de papa con salsa 4 quesos', 15000, 70, 1, 10, 1),
(4, 'Lasagna de carne', 'Capas de pasta con carne, bechamel y mozzarella', 16500, 60, 1, 9, 1),

-- Carnes
(5, 'Bife de chorizo', 'Bife de chorizo a la parrilla con guarnición', 22000, 50, 2, 11, 2),
(6, 'Pollo al limón', 'Suprema de pollo grillada con salsa de limón', 18000, 60, 2, 12, 2),
(7, 'Asado de tira', 'Clásico asado argentino con papas fritas', 24000, 40, 2, 11, 1),
(8, 'Paella de mariscos', 'Arroz con mariscos y azafrán', 26000, 30, 2, 13, 2),

-- Bebidas
(9, 'Coca Cola 500ml', 'Gaseosa Coca Cola individual', 3000, 200, 3, 14,2),
(10, 'Agua sin gas 500ml', 'Agua mineral sin gas', 2500, 150, 3, 17,1),
(11, 'Vino Malbec 750ml', 'Botella de vino Malbec reserva', 12000, 30, 3, 15,2),
(12, 'Cerveza artesanal 500ml', 'IPA artesanal de la casa', 5000, 80, 3, 16,1),
(13, 'Sprite 500ml', 'Gaseosa lima-limón', 3000, 120, 3, 14,2),
(14, 'Vino Cabernet Sauvignon 750ml', 'Vino tinto reserva', 14000, 25, 3, 15,1),

-- Postres
(15, 'Helado de chocolate', 'Helado artesanal de chocolate', 4000, 60, 4, 18,1),
(16, 'Tiramisú', 'Postre italiano clásico con café y cacao', 6500, 40, 4, 19,1),
(17, 'Flan casero', 'Flan con dulce de leche y crema', 4500, 70, 4, 20,2),
(18, 'Cheesecake frutos rojos', 'Tarta de queso con frutos rojos', 7000, 30, 4, 19,2),

-- Entradas
(19, 'Picada para 2', 'Quesos, fiambres y aceitunas', 12000, 25, 5, 21,2),
(20, 'Empanadas de carne', 'Empanadas tradicionales fritas o al horno', 2500, 100, 5, 22,2),
(21, 'Empanadas de jamón y queso', 'Empanadas clásicas de jamón y muzzarella', 2500, 100, 5, 22,1),
(22, 'Sopa crema de calabaza', 'Sopa suave de calabaza y especias', 6000, 20, 5, 23,2),

-- Pizzas
(23, 'Pizza muzzarella', 'Pizza clásica con muzzarella y tomate', 10000, 50, 6, 24,2),
(24, 'Pizza napolitana', 'Muzzarella, tomate y ajo fresco', 11000, 40, 6, 24,1),
(25, 'Pizza fugazzeta', 'Pizza rellena de muzzarella y cebolla', 12000, 35, 6, 24,1),
(26, 'Pizza especial de la casa', 'Jamón, morrones, huevo y aceitunas', 13500, 25, 6, 25,2),

-- Hamburguesas
(27, 'Hamburguesa clásica', 'Medallón de carne, lechuga, tomate y cheddar', 9500, 60, 7, 26,2),
(28, 'Hamburguesa doble cheddar', 'Doble carne, doble cheddar, panceta', 12500, 45, 7, 27,1),
(29, 'Hamburguesa veggie', 'Medallón de lentejas, palta y vegetales', 10500, 40, 7, 27,1);
