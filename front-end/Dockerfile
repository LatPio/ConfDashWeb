FROM node:latest as build
WORKDIR usr/src/app
COPY package.json package-lock.json ./
RUN npm install
COPY . .
RUN npm run build

FROM nginx:latest
COPY nginx.conf /etc/nginx/nginx.conf
COPY --from=build usr/src/app/dist/front-end /usr/share/nginx/html
#EXPOSE 4200
#CMD ng serve --host=0.0.0.0 --port=8080





#FROM node:latest as build
#WORKDIR /app
#ENV PATH /app/node_modules/.bin:$PATH
#
#COPY package.json /app/package.json
#RUN npm install
#RUN npm install -g @angular/cli
#
#COPY . /app
#RUN #npm run build
#
##FROM nginx:latest
##COPY --from=build /app/dist/front-end /usr/share/nginx/html
##EXPOSE 4200
#CMD ng serve --host=0.0.0.0 --port=8080
