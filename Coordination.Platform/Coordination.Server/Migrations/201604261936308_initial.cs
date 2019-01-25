namespace Coordination.Server.Migrations
{
    using System;
    using System.Data.Entity.Migrations;
    
    public partial class initial : DbMigration
    {
        public override void Up()
        {
            CreateTable(
                "dbo.JobAssignments",
                c => new
                    {
                        ID = c.Int(nullable: false, identity: true),
                        Job_ID = c.Int(),
                        User_ID = c.Int(),
                    })
                .PrimaryKey(t => t.ID)
                .ForeignKey("dbo.Jobs", t => t.Job_ID)
                .ForeignKey("dbo.Users", t => t.User_ID)
                .Index(t => t.Job_ID)
                .Index(t => t.User_ID);
            
            CreateTable(
                "dbo.Jobs",
                c => new
                    {
                        ID = c.Int(nullable: false, identity: true),
                        Latitude = c.String(),
                        Longitude = c.String(),
                        Description = c.String(),
                        Completed = c.Boolean(),
                        Assigned = c.Boolean(),
                        JobName = c.String(),
                    })
                .PrimaryKey(t => t.ID);
            
            CreateTable(
                "dbo.Users",
                c => new
                    {
                        ID = c.Int(nullable: false, identity: true),
                        Username = c.String(),
                        Password = c.String(),
                        Email = c.String(),
                        Latitude = c.String(),
                        Longitude = c.String(),
                    })
                .PrimaryKey(t => t.ID);
            
        }
        
        public override void Down()
        {
            DropForeignKey("dbo.JobAssignments", "User_ID", "dbo.Users");
            DropForeignKey("dbo.JobAssignments", "Job_ID", "dbo.Jobs");
            DropIndex("dbo.JobAssignments", new[] { "User_ID" });
            DropIndex("dbo.JobAssignments", new[] { "Job_ID" });
            DropTable("dbo.Users");
            DropTable("dbo.Jobs");
            DropTable("dbo.JobAssignments");
        }
    }
}
