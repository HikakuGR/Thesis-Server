namespace Coordination.Server.Migrations
{
    using System;
    using System.Data.Entity.Migrations;
    
    public partial class jobAssignmentApproved : DbMigration
    {
        public override void Up()
        {
            AddColumn("dbo.JobAssignments", "Approved", c => c.Boolean(nullable: false));
        }
        
        public override void Down()
        {
            DropColumn("dbo.JobAssignments", "Approved");
        }
    }
}
